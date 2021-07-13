package com.deltaqin.sys.model;

import java.util.ArrayList;
import java.util.List;

public class SysRemoteInfluxPanalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysRemoteInfluxPanalExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCidIsNull() {
            addCriterion("cid is null");
            return (Criteria) this;
        }

        public Criteria andCidIsNotNull() {
            addCriterion("cid is not null");
            return (Criteria) this;
        }

        public Criteria andCidEqualTo(Long value) {
            addCriterion("cid =", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotEqualTo(Long value) {
            addCriterion("cid <>", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThan(Long value) {
            addCriterion("cid >", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThanOrEqualTo(Long value) {
            addCriterion("cid >=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThan(Long value) {
            addCriterion("cid <", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThanOrEqualTo(Long value) {
            addCriterion("cid <=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidIn(List<Long> values) {
            addCriterion("cid in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotIn(List<Long> values) {
            addCriterion("cid not in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidBetween(Long value1, Long value2) {
            addCriterion("cid between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotBetween(Long value1, Long value2) {
            addCriterion("cid not between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andDbIdIsNull() {
            addCriterion("db_id is null");
            return (Criteria) this;
        }

        public Criteria andDbIdIsNotNull() {
            addCriterion("db_id is not null");
            return (Criteria) this;
        }

        public Criteria andDbIdEqualTo(Long value) {
            addCriterion("db_id =", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdNotEqualTo(Long value) {
            addCriterion("db_id <>", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdGreaterThan(Long value) {
            addCriterion("db_id >", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdGreaterThanOrEqualTo(Long value) {
            addCriterion("db_id >=", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdLessThan(Long value) {
            addCriterion("db_id <", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdLessThanOrEqualTo(Long value) {
            addCriterion("db_id <=", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdIn(List<Long> values) {
            addCriterion("db_id in", values, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdNotIn(List<Long> values) {
            addCriterion("db_id not in", values, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdBetween(Long value1, Long value2) {
            addCriterion("db_id between", value1, value2, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdNotBetween(Long value1, Long value2) {
            addCriterion("db_id not between", value1, value2, "dbId");
            return (Criteria) this;
        }

        public Criteria andSqlStatementIsNull() {
            addCriterion("sql_statement is null");
            return (Criteria) this;
        }

        public Criteria andSqlStatementIsNotNull() {
            addCriterion("sql_statement is not null");
            return (Criteria) this;
        }

        public Criteria andSqlStatementEqualTo(String value) {
            addCriterion("sql_statement =", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementNotEqualTo(String value) {
            addCriterion("sql_statement <>", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementGreaterThan(String value) {
            addCriterion("sql_statement >", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementGreaterThanOrEqualTo(String value) {
            addCriterion("sql_statement >=", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementLessThan(String value) {
            addCriterion("sql_statement <", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementLessThanOrEqualTo(String value) {
            addCriterion("sql_statement <=", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementLike(String value) {
            addCriterion("sql_statement like", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementNotLike(String value) {
            addCriterion("sql_statement not like", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementIn(List<String> values) {
            addCriterion("sql_statement in", values, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementNotIn(List<String> values) {
            addCriterion("sql_statement not in", values, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementBetween(String value1, String value2) {
            addCriterion("sql_statement between", value1, value2, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementNotBetween(String value1, String value2) {
            addCriterion("sql_statement not between", value1, value2, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andPanalTypeIdIsNull() {
            addCriterion("panal_type_id is null");
            return (Criteria) this;
        }

        public Criteria andPanalTypeIdIsNotNull() {
            addCriterion("panal_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andPanalTypeIdEqualTo(Long value) {
            addCriterion("panal_type_id =", value, "panalTypeId");
            return (Criteria) this;
        }

        public Criteria andPanalTypeIdNotEqualTo(Long value) {
            addCriterion("panal_type_id <>", value, "panalTypeId");
            return (Criteria) this;
        }

        public Criteria andPanalTypeIdGreaterThan(Long value) {
            addCriterion("panal_type_id >", value, "panalTypeId");
            return (Criteria) this;
        }

        public Criteria andPanalTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("panal_type_id >=", value, "panalTypeId");
            return (Criteria) this;
        }

        public Criteria andPanalTypeIdLessThan(Long value) {
            addCriterion("panal_type_id <", value, "panalTypeId");
            return (Criteria) this;
        }

        public Criteria andPanalTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("panal_type_id <=", value, "panalTypeId");
            return (Criteria) this;
        }

        public Criteria andPanalTypeIdIn(List<Long> values) {
            addCriterion("panal_type_id in", values, "panalTypeId");
            return (Criteria) this;
        }

        public Criteria andPanalTypeIdNotIn(List<Long> values) {
            addCriterion("panal_type_id not in", values, "panalTypeId");
            return (Criteria) this;
        }

        public Criteria andPanalTypeIdBetween(Long value1, Long value2) {
            addCriterion("panal_type_id between", value1, value2, "panalTypeId");
            return (Criteria) this;
        }

        public Criteria andPanalTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("panal_type_id not between", value1, value2, "panalTypeId");
            return (Criteria) this;
        }

        public Criteria andPanalNameIsNull() {
            addCriterion("panal_name is null");
            return (Criteria) this;
        }

        public Criteria andPanalNameIsNotNull() {
            addCriterion("panal_name is not null");
            return (Criteria) this;
        }

        public Criteria andPanalNameEqualTo(String value) {
            addCriterion("panal_name =", value, "panalName");
            return (Criteria) this;
        }

        public Criteria andPanalNameNotEqualTo(String value) {
            addCriterion("panal_name <>", value, "panalName");
            return (Criteria) this;
        }

        public Criteria andPanalNameGreaterThan(String value) {
            addCriterion("panal_name >", value, "panalName");
            return (Criteria) this;
        }

        public Criteria andPanalNameGreaterThanOrEqualTo(String value) {
            addCriterion("panal_name >=", value, "panalName");
            return (Criteria) this;
        }

        public Criteria andPanalNameLessThan(String value) {
            addCriterion("panal_name <", value, "panalName");
            return (Criteria) this;
        }

        public Criteria andPanalNameLessThanOrEqualTo(String value) {
            addCriterion("panal_name <=", value, "panalName");
            return (Criteria) this;
        }

        public Criteria andPanalNameLike(String value) {
            addCriterion("panal_name like", value, "panalName");
            return (Criteria) this;
        }

        public Criteria andPanalNameNotLike(String value) {
            addCriterion("panal_name not like", value, "panalName");
            return (Criteria) this;
        }

        public Criteria andPanalNameIn(List<String> values) {
            addCriterion("panal_name in", values, "panalName");
            return (Criteria) this;
        }

        public Criteria andPanalNameNotIn(List<String> values) {
            addCriterion("panal_name not in", values, "panalName");
            return (Criteria) this;
        }

        public Criteria andPanalNameBetween(String value1, String value2) {
            addCriterion("panal_name between", value1, value2, "panalName");
            return (Criteria) this;
        }

        public Criteria andPanalNameNotBetween(String value1, String value2) {
            addCriterion("panal_name not between", value1, value2, "panalName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}