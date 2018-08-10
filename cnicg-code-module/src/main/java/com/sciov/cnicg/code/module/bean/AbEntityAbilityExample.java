package com.sciov.cnicg.code.module.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AbEntityAbilityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AbEntityAbilityExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAbilityNameIsNull() {
            addCriterion("ability_name is null");
            return (Criteria) this;
        }

        public Criteria andAbilityNameIsNotNull() {
            addCriterion("ability_name is not null");
            return (Criteria) this;
        }

        public Criteria andAbilityNameEqualTo(String value) {
            addCriterion("ability_name =", value, "abilityName");
            return (Criteria) this;
        }

        public Criteria andAbilityNameNotEqualTo(String value) {
            addCriterion("ability_name <>", value, "abilityName");
            return (Criteria) this;
        }

        public Criteria andAbilityNameGreaterThan(String value) {
            addCriterion("ability_name >", value, "abilityName");
            return (Criteria) this;
        }

        public Criteria andAbilityNameGreaterThanOrEqualTo(String value) {
            addCriterion("ability_name >=", value, "abilityName");
            return (Criteria) this;
        }

        public Criteria andAbilityNameLessThan(String value) {
            addCriterion("ability_name <", value, "abilityName");
            return (Criteria) this;
        }

        public Criteria andAbilityNameLessThanOrEqualTo(String value) {
            addCriterion("ability_name <=", value, "abilityName");
            return (Criteria) this;
        }

        public Criteria andAbilityNameLike(String value) {
            addCriterion("ability_name like", value, "abilityName");
            return (Criteria) this;
        }

        public Criteria andAbilityNameNotLike(String value) {
            addCriterion("ability_name not like", value, "abilityName");
            return (Criteria) this;
        }

        public Criteria andAbilityNameIn(List<String> values) {
            addCriterion("ability_name in", values, "abilityName");
            return (Criteria) this;
        }

        public Criteria andAbilityNameNotIn(List<String> values) {
            addCriterion("ability_name not in", values, "abilityName");
            return (Criteria) this;
        }

        public Criteria andAbilityNameBetween(String value1, String value2) {
            addCriterion("ability_name between", value1, value2, "abilityName");
            return (Criteria) this;
        }

        public Criteria andAbilityNameNotBetween(String value1, String value2) {
            addCriterion("ability_name not between", value1, value2, "abilityName");
            return (Criteria) this;
        }

        public Criteria andAbilityScoreIsNull() {
            addCriterion("ability_score is null");
            return (Criteria) this;
        }

        public Criteria andAbilityScoreIsNotNull() {
            addCriterion("ability_score is not null");
            return (Criteria) this;
        }

        public Criteria andAbilityScoreEqualTo(Integer value) {
            addCriterion("ability_score =", value, "abilityScore");
            return (Criteria) this;
        }

        public Criteria andAbilityScoreNotEqualTo(Integer value) {
            addCriterion("ability_score <>", value, "abilityScore");
            return (Criteria) this;
        }

        public Criteria andAbilityScoreGreaterThan(Integer value) {
            addCriterion("ability_score >", value, "abilityScore");
            return (Criteria) this;
        }

        public Criteria andAbilityScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("ability_score >=", value, "abilityScore");
            return (Criteria) this;
        }

        public Criteria andAbilityScoreLessThan(Integer value) {
            addCriterion("ability_score <", value, "abilityScore");
            return (Criteria) this;
        }

        public Criteria andAbilityScoreLessThanOrEqualTo(Integer value) {
            addCriterion("ability_score <=", value, "abilityScore");
            return (Criteria) this;
        }

        public Criteria andAbilityScoreIn(List<Integer> values) {
            addCriterion("ability_score in", values, "abilityScore");
            return (Criteria) this;
        }

        public Criteria andAbilityScoreNotIn(List<Integer> values) {
            addCriterion("ability_score not in", values, "abilityScore");
            return (Criteria) this;
        }

        public Criteria andAbilityScoreBetween(Integer value1, Integer value2) {
            addCriterion("ability_score between", value1, value2, "abilityScore");
            return (Criteria) this;
        }

        public Criteria andAbilityScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("ability_score not between", value1, value2, "abilityScore");
            return (Criteria) this;
        }

        public Criteria andAbilityStatusIsNull() {
            addCriterion("ability_status is null");
            return (Criteria) this;
        }

        public Criteria andAbilityStatusIsNotNull() {
            addCriterion("ability_status is not null");
            return (Criteria) this;
        }

        public Criteria andAbilityStatusEqualTo(Integer value) {
            addCriterion("ability_status =", value, "abilityStatus");
            return (Criteria) this;
        }

        public Criteria andAbilityStatusNotEqualTo(Integer value) {
            addCriterion("ability_status <>", value, "abilityStatus");
            return (Criteria) this;
        }

        public Criteria andAbilityStatusGreaterThan(Integer value) {
            addCriterion("ability_status >", value, "abilityStatus");
            return (Criteria) this;
        }

        public Criteria andAbilityStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("ability_status >=", value, "abilityStatus");
            return (Criteria) this;
        }

        public Criteria andAbilityStatusLessThan(Integer value) {
            addCriterion("ability_status <", value, "abilityStatus");
            return (Criteria) this;
        }

        public Criteria andAbilityStatusLessThanOrEqualTo(Integer value) {
            addCriterion("ability_status <=", value, "abilityStatus");
            return (Criteria) this;
        }

        public Criteria andAbilityStatusIn(List<Integer> values) {
            addCriterion("ability_status in", values, "abilityStatus");
            return (Criteria) this;
        }

        public Criteria andAbilityStatusNotIn(List<Integer> values) {
            addCriterion("ability_status not in", values, "abilityStatus");
            return (Criteria) this;
        }

        public Criteria andAbilityStatusBetween(Integer value1, Integer value2) {
            addCriterion("ability_status between", value1, value2, "abilityStatus");
            return (Criteria) this;
        }

        public Criteria andAbilityStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("ability_status not between", value1, value2, "abilityStatus");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
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