package ehb.finalwork.manager.model;

public abstract class ModelTemplate {

        protected String id;

        public abstract String getTableName();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
