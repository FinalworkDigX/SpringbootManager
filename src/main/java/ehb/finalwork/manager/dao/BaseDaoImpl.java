package ehb.finalwork.manager.dao;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Cursor;
import ehb.finalwork.manager.dao.database.RethinkDBConnectionFactory;
import ehb.finalwork.manager.error.CustomNotFoundException;
import ehb.finalwork.manager.model.ModelTemplate;
import ehb.finalwork.manager.model.RethinkDBHashable;
import ehb.finalwork.manager.model.RethinkReturnObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseDaoImpl<T extends ModelTemplate, U extends RethinkDBHashable> implements BaseDao<T, U> {
    protected static final RethinkDB r = RethinkDB.r;
    protected Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RethinkDBConnectionFactory connectionFactory;

    private T entity;

    BaseDaoImpl(T entity) {
        this.entity = entity;
    }

    @Override
    public List<T> getAll() {
        Cursor<T> cursor = r.db("manager")
                            .table(this.entity.getTableName())
                            .run(connectionFactory.createConnection(), entity.getClass());

        return cursor.toList();
    }

    @Override
    public T getById(String id) throws CustomNotFoundException {
        T entity = r.db("manager")
                .table(this.entity.getTableName())
                .get(id)
                .run(connectionFactory.createConnection(), this.entity.getClass());

        if (entity != null) {
            return entity;
        }
        throw new CustomNotFoundException("Room with id: '" + id + "' not found.");
    }

    @Override
    public T create(U entityDto) throws Exception {
        RethinkReturnObject returnObject = r.db("manager")
                                            .table(entity.getTableName())
                                            .insert(entityDto.toHashMap())
                                            .optArg("return_changes", true)
                                            .run(connectionFactory.createConnection(), RethinkReturnObject.class);

        if (returnObject.getInserted() != 0) {
            return (T) returnObject.getFirstNewVal(this.entity.getClass());
        }
        // Create custom exception
        throw new Exception();
    }

    @Override
    public T update(T entity) {
        RethinkReturnObject returnObject = r.db("manager")
                                            .table(entity.getTableName())
                                            .get(entity.getId())
                                            .update(entity.toHashMap())
                                            .optArg("return_changes", true)
                                            .run(connectionFactory.createConnection(), RethinkReturnObject.class);

        if (returnObject.getReplaced() != 0) {
            entity = (T) returnObject.getFirstNewVal(this.entity.getClass());
        }
        else {
            log.error("DataItemNotUpdated: {}", returnObject);
        }

        return entity;
    }

    @Override
    public void delete(String id) throws CustomNotFoundException {
        RethinkReturnObject returnObject = r.db("manager")
                                            .table(entity.getTableName())
                                            .get(id)
                                            .delete().optArg("return_changes", true)
                                            .run(connectionFactory.createConnection(), RethinkReturnObject.class);

        log.info("Delete {}", returnObject);
        log.info("Delete id {}", id);
        if (returnObject.getDeleted() == 0) {
            throw new CustomNotFoundException("DataItem with id: " + id + ": Not Found");
        }
    }
}
