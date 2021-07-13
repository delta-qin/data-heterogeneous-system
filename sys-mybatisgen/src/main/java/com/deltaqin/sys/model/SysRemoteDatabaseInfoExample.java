package com.deltaqin.sys.model;

import java.util.ArrayList;
import java.util.List;

public class SysRemoteDatabaseInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysRemoteDatabaseInfoExample() {
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

        public Criteria andDbUrlIsNull() {
            addCriterion("db_url is null");
            return (Criteria) this;
        }

        public Criteria andDbUrlIsNotNull() {
            addCriterion("db_url is not null");
            return (Criteria) this;
        }

        public Criteria andDbUrlEqualTo(String value) {
            addCriterion("db_url =", value, "dbUrl");
            return (Criteria) this;
        }

        public Criteria andDbUrlNotEqualTo(String value) {
            addCriterion("db_url <>", value, "dbUrl");
            return (Criteria) this;
        }

        public Criteria andDbUrlGreaterThan(String value) {
            addCriterion("db_url >", value, "dbUrl");
            return (Criteria) this;
        }

        public Criteria andDbUrlGreaterThanOrEqualTo(String value) {
            addCriterion("db_url >=", value, "dbUrl");
            return (Criteria) this;
        }

        public Criteria andDbUrlLessThan(String value) {
            addCriterion("db_url <", value, "dbUrl");
            return (Criteria) this;
        }

        public Criteria andDbUrlLessThanOrEqualTo(String value) {
            addCriterion("db_url <=", value, "dbUrl");
            return (Criteria) this;
        }

        public Criteria andDbUrlLike(String value) {
            addCriterion("db_url like", value, "dbUrl");
            return (Criteria) this;
        }

        public Criteria andDbUrlNotLike(String value) {
            addCriterion("db_url not like", value, "dbUrl");
            return (Criteria) this;
        }

        public Criteria andDbUrlIn(List<String> values) {
            addCriterion("db_url in", values, "dbUrl");
            return (Criteria) this;
        }

        public Criteria andDbUrlNotIn(List<String> values) {
            addCriterion("db_url not in", values, "dbUrl");
            return (Criteria) this;
        }

        public Criteria andDbUrlBetween(String value1, String value2) {
            addCriterion("db_url between", value1, value2, "dbUrl");
            return (Criteria) this;
        }

        public Criteria andDbUrlNotBetween(String value1, String value2) {
            addCriterion("db_url not between", value1, value2, "dbUrl");
            return (Criteria) this;
        }

        public Criteria andDbUserIsNull() {
            addCriterion("db_user is null");
            return (Criteria) this;
        }

        public Criteria andDbUserIsNotNull() {
            addCriterion("db_user is not null");
            return (Criteria) this;
        }

        public Criteria andDbUserEqualTo(String value) {
            addCriterion("db_user =", value, "dbUser");
            return (Criteria) this;
        }

        public Criteria andDbUserNotEqualTo(String value) {
            addCriterion("db_user <>", value, "dbUser");
            return (Criteria) this;
        }

        public Criteria andDbUserGreaterThan(String value) {
            addCriterion("db_user >", value, "dbUser");
            return (Criteria) this;
        }

        public Criteria andDbUserGreaterThanOrEqualTo(String value) {
            addCriterion("db_user >=", value, "dbUser");
            return (Criteria) this;
        }

        public Criteria andDbUserLessThan(String value) {
            addCriterion("db_user <", value, "dbUser");
            return (Criteria) this;
        }

        public Criteria andDbUserLessThanOrEqualTo(String value) {
            addCriterion("db_user <=", value, "dbUser");
            return (Criteria) this;
        }

        public Criteria andDbUserLike(String value) {
            addCriterion("db_user like", value, "dbUser");
            return (Criteria) this;
        }

        public Criteria andDbUserNotLike(String value) {
            addCriterion("db_user not like", value, "dbUser");
            return (Criteria) this;
        }

        public Criteria andDbUserIn(List<String> values) {
            addCriterion("db_user in", values, "dbUser");
            return (Criteria) this;
        }

        public Criteria andDbUserNotIn(List<String> values) {
            addCriterion("db_user not in", values, "dbUser");
            return (Criteria) this;
        }

        public Criteria andDbUserBetween(String value1, String value2) {
            addCriterion("db_user between", value1, value2, "dbUser");
            return (Criteria) this;
        }

        public Criteria andDbUserNotBetween(String value1, String value2) {
            addCriterion("db_user not between", value1, value2, "dbUser");
            return (Criteria) this;
        }

        public Criteria andDbPasswordIsNull() {
            addCriterion("db_password is null");
            return (Criteria) this;
        }

        public Criteria andDbPasswordIsNotNull() {
            addCriterion("db_password is not null");
            return (Criteria) this;
        }

        public Criteria andDbPasswordEqualTo(String value) {
            addCriterion("db_password =", value, "dbPassword");
            return (Criteria) this;
        }

        public Criteria andDbPasswordNotEqualTo(String value) {
            addCriterion("db_password <>", value, "dbPassword");
            return (Criteria) this;
        }

        public Criteria andDbPasswordGreaterThan(String value) {
            addCriterion("db_password >", value, "dbPassword");
            return (Criteria) this;
        }

        public Criteria andDbPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("db_password >=", value, "dbPassword");
            return (Criteria) this;
        }

        public Criteria andDbPasswordLessThan(String value) {
            addCriterion("db_password <", value, "dbPassword");
            return (Criteria) this;
        }

        public Criteria andDbPasswordLessThanOrEqualTo(String value) {
            addCriterion("db_password <=", value, "dbPassword");
            return (Criteria) this;
        }

        public Criteria andDbPasswordLike(String value) {
            addCriterion("db_password like", value, "dbPassword");
            return (Criteria) this;
        }

        public Criteria andDbPasswordNotLike(String value) {
            addCriterion("db_password not like", value, "dbPassword");
            return (Criteria) this;
        }

        public Criteria andDbPasswordIn(List<String> values) {
            addCriterion("db_password in", values, "dbPassword");
            return (Criteria) this;
        }

        public Criteria andDbPasswordNotIn(List<String> values) {
            addCriterion("db_password not in", values, "dbPassword");
            return (Criteria) this;
        }

        public Criteria andDbPasswordBetween(String value1, String value2) {
            addCriterion("db_password between", value1, value2, "dbPassword");
            return (Criteria) this;
        }

        public Criteria andDbPasswordNotBetween(String value1, String value2) {
            addCriterion("db_password not between", value1, value2, "dbPassword");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionIsNull() {
            addCriterion("db_description is null");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionIsNotNull() {
            addCriterion("db_description is not null");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionEqualTo(String value) {
            addCriterion("db_description =", value, "dbDescription");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionNotEqualTo(String value) {
            addCriterion("db_description <>", value, "dbDescription");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionGreaterThan(String value) {
            addCriterion("db_description >", value, "dbDescription");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("db_description >=", value, "dbDescription");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionLessThan(String value) {
            addCriterion("db_description <", value, "dbDescription");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionLessThanOrEqualTo(String value) {
            addCriterion("db_description <=", value, "dbDescription");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionLike(String value) {
            addCriterion("db_description like", value, "dbDescription");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionNotLike(String value) {
            addCriterion("db_description not like", value, "dbDescription");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionIn(List<String> values) {
            addCriterion("db_description in", values, "dbDescription");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionNotIn(List<String> values) {
            addCriterion("db_description not in", values, "dbDescription");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionBetween(String value1, String value2) {
            addCriterion("db_description between", value1, value2, "dbDescription");
            return (Criteria) this;
        }

        public Criteria andDbDescriptionNotBetween(String value1, String value2) {
            addCriterion("db_description not between", value1, value2, "dbDescription");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("type_id is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("type_id is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Long value) {
            addCriterion("type_id =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Long value) {
            addCriterion("type_id <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Long value) {
            addCriterion("type_id >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("type_id >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Long value) {
            addCriterion("type_id <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("type_id <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Long> values) {
            addCriterion("type_id in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Long> values) {
            addCriterion("type_id not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Long value1, Long value2) {
            addCriterion("type_id between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("type_id not between", value1, value2, "typeId");
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