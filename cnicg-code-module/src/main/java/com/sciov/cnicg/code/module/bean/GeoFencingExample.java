package com.sciov.cnicg.code.module.bean;

import java.util.ArrayList;
import java.util.List;

public class GeoFencingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GeoFencingExample() {
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

        public Criteria andAdcodeIsNull() {
            addCriterion("adcode is null");
            return (Criteria) this;
        }

        public Criteria andAdcodeIsNotNull() {
            addCriterion("adcode is not null");
            return (Criteria) this;
        }

        public Criteria andAdcodeEqualTo(String value) {
            addCriterion("adcode =", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeNotEqualTo(String value) {
            addCriterion("adcode <>", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeGreaterThan(String value) {
            addCriterion("adcode >", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeGreaterThanOrEqualTo(String value) {
            addCriterion("adcode >=", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeLessThan(String value) {
            addCriterion("adcode <", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeLessThanOrEqualTo(String value) {
            addCriterion("adcode <=", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeLike(String value) {
            addCriterion("adcode like", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeNotLike(String value) {
            addCriterion("adcode not like", value, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeIn(List<String> values) {
            addCriterion("adcode in", values, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeNotIn(List<String> values) {
            addCriterion("adcode not in", values, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeBetween(String value1, String value2) {
            addCriterion("adcode between", value1, value2, "adcode");
            return (Criteria) this;
        }

        public Criteria andAdcodeNotBetween(String value1, String value2) {
            addCriterion("adcode not between", value1, value2, "adcode");
            return (Criteria) this;
        }

        public Criteria andFenceGidIsNull() {
            addCriterion("fence_gid is null");
            return (Criteria) this;
        }

        public Criteria andFenceGidIsNotNull() {
            addCriterion("fence_gid is not null");
            return (Criteria) this;
        }

        public Criteria andFenceGidEqualTo(String value) {
            addCriterion("fence_gid =", value, "fenceGid");
            return (Criteria) this;
        }

        public Criteria andFenceGidNotEqualTo(String value) {
            addCriterion("fence_gid <>", value, "fenceGid");
            return (Criteria) this;
        }

        public Criteria andFenceGidGreaterThan(String value) {
            addCriterion("fence_gid >", value, "fenceGid");
            return (Criteria) this;
        }

        public Criteria andFenceGidGreaterThanOrEqualTo(String value) {
            addCriterion("fence_gid >=", value, "fenceGid");
            return (Criteria) this;
        }

        public Criteria andFenceGidLessThan(String value) {
            addCriterion("fence_gid <", value, "fenceGid");
            return (Criteria) this;
        }

        public Criteria andFenceGidLessThanOrEqualTo(String value) {
            addCriterion("fence_gid <=", value, "fenceGid");
            return (Criteria) this;
        }

        public Criteria andFenceGidLike(String value) {
            addCriterion("fence_gid like", value, "fenceGid");
            return (Criteria) this;
        }

        public Criteria andFenceGidNotLike(String value) {
            addCriterion("fence_gid not like", value, "fenceGid");
            return (Criteria) this;
        }

        public Criteria andFenceGidIn(List<String> values) {
            addCriterion("fence_gid in", values, "fenceGid");
            return (Criteria) this;
        }

        public Criteria andFenceGidNotIn(List<String> values) {
            addCriterion("fence_gid not in", values, "fenceGid");
            return (Criteria) this;
        }

        public Criteria andFenceGidBetween(String value1, String value2) {
            addCriterion("fence_gid between", value1, value2, "fenceGid");
            return (Criteria) this;
        }

        public Criteria andFenceGidNotBetween(String value1, String value2) {
            addCriterion("fence_gid not between", value1, value2, "fenceGid");
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