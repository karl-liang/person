package com.sciov.cnicg.code.module.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AbEntityClassExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AbEntityClassExample() {
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

        public Criteria andClassNameIsNull() {
            addCriterion("class_name is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("class_name is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("class_name =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("class_name <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("class_name >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("class_name >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("class_name <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("class_name <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("class_name like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("class_name not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("class_name in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("class_name not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("class_name between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("class_name not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassStatusIsNull() {
            addCriterion("class_status is null");
            return (Criteria) this;
        }

        public Criteria andClassStatusIsNotNull() {
            addCriterion("class_status is not null");
            return (Criteria) this;
        }

        public Criteria andClassStatusEqualTo(Integer value) {
            addCriterion("class_status =", value, "classStatus");
            return (Criteria) this;
        }

        public Criteria andClassStatusNotEqualTo(Integer value) {
            addCriterion("class_status <>", value, "classStatus");
            return (Criteria) this;
        }

        public Criteria andClassStatusGreaterThan(Integer value) {
            addCriterion("class_status >", value, "classStatus");
            return (Criteria) this;
        }

        public Criteria andClassStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_status >=", value, "classStatus");
            return (Criteria) this;
        }

        public Criteria andClassStatusLessThan(Integer value) {
            addCriterion("class_status <", value, "classStatus");
            return (Criteria) this;
        }

        public Criteria andClassStatusLessThanOrEqualTo(Integer value) {
            addCriterion("class_status <=", value, "classStatus");
            return (Criteria) this;
        }

        public Criteria andClassStatusIn(List<Integer> values) {
            addCriterion("class_status in", values, "classStatus");
            return (Criteria) this;
        }

        public Criteria andClassStatusNotIn(List<Integer> values) {
            addCriterion("class_status not in", values, "classStatus");
            return (Criteria) this;
        }

        public Criteria andClassStatusBetween(Integer value1, Integer value2) {
            addCriterion("class_status between", value1, value2, "classStatus");
            return (Criteria) this;
        }

        public Criteria andClassStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("class_status not between", value1, value2, "classStatus");
            return (Criteria) this;
        }

        public Criteria andAbilityIdIsNull() {
            addCriterion("ability_id is null");
            return (Criteria) this;
        }

        public Criteria andAbilityIdIsNotNull() {
            addCriterion("ability_id is not null");
            return (Criteria) this;
        }

        public Criteria andAbilityIdEqualTo(Integer value) {
            addCriterion("ability_id =", value, "abilityId");
            return (Criteria) this;
        }

        public Criteria andAbilityIdNotEqualTo(Integer value) {
            addCriterion("ability_id <>", value, "abilityId");
            return (Criteria) this;
        }

        public Criteria andAbilityIdGreaterThan(Integer value) {
            addCriterion("ability_id >", value, "abilityId");
            return (Criteria) this;
        }

        public Criteria andAbilityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ability_id >=", value, "abilityId");
            return (Criteria) this;
        }

        public Criteria andAbilityIdLessThan(Integer value) {
            addCriterion("ability_id <", value, "abilityId");
            return (Criteria) this;
        }

        public Criteria andAbilityIdLessThanOrEqualTo(Integer value) {
            addCriterion("ability_id <=", value, "abilityId");
            return (Criteria) this;
        }

        public Criteria andAbilityIdIn(List<Integer> values) {
            addCriterion("ability_id in", values, "abilityId");
            return (Criteria) this;
        }

        public Criteria andAbilityIdNotIn(List<Integer> values) {
            addCriterion("ability_id not in", values, "abilityId");
            return (Criteria) this;
        }

        public Criteria andAbilityIdBetween(Integer value1, Integer value2) {
            addCriterion("ability_id between", value1, value2, "abilityId");
            return (Criteria) this;
        }

        public Criteria andAbilityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ability_id not between", value1, value2, "abilityId");
            return (Criteria) this;
        }

        public Criteria andTargetScoreIsNull() {
            addCriterion("target_score is null");
            return (Criteria) this;
        }

        public Criteria andTargetScoreIsNotNull() {
            addCriterion("target_score is not null");
            return (Criteria) this;
        }

        public Criteria andTargetScoreEqualTo(Integer value) {
            addCriterion("target_score =", value, "targetScore");
            return (Criteria) this;
        }

        public Criteria andTargetScoreNotEqualTo(Integer value) {
            addCriterion("target_score <>", value, "targetScore");
            return (Criteria) this;
        }

        public Criteria andTargetScoreGreaterThan(Integer value) {
            addCriterion("target_score >", value, "targetScore");
            return (Criteria) this;
        }

        public Criteria andTargetScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("target_score >=", value, "targetScore");
            return (Criteria) this;
        }

        public Criteria andTargetScoreLessThan(Integer value) {
            addCriterion("target_score <", value, "targetScore");
            return (Criteria) this;
        }

        public Criteria andTargetScoreLessThanOrEqualTo(Integer value) {
            addCriterion("target_score <=", value, "targetScore");
            return (Criteria) this;
        }

        public Criteria andTargetScoreIn(List<Integer> values) {
            addCriterion("target_score in", values, "targetScore");
            return (Criteria) this;
        }

        public Criteria andTargetScoreNotIn(List<Integer> values) {
            addCriterion("target_score not in", values, "targetScore");
            return (Criteria) this;
        }

        public Criteria andTargetScoreBetween(Integer value1, Integer value2) {
            addCriterion("target_score between", value1, value2, "targetScore");
            return (Criteria) this;
        }

        public Criteria andTargetScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("target_score not between", value1, value2, "targetScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreIsNull() {
            addCriterion("current_score is null");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreIsNotNull() {
            addCriterion("current_score is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreEqualTo(Integer value) {
            addCriterion("current_score =", value, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreNotEqualTo(Integer value) {
            addCriterion("current_score <>", value, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreGreaterThan(Integer value) {
            addCriterion("current_score >", value, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_score >=", value, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreLessThan(Integer value) {
            addCriterion("current_score <", value, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreLessThanOrEqualTo(Integer value) {
            addCriterion("current_score <=", value, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreIn(List<Integer> values) {
            addCriterion("current_score in", values, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreNotIn(List<Integer> values) {
            addCriterion("current_score not in", values, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreBetween(Integer value1, Integer value2) {
            addCriterion("current_score between", value1, value2, "currentScore");
            return (Criteria) this;
        }

        public Criteria andCurrentScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("current_score not between", value1, value2, "currentScore");
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

        public Criteria andGmtPlanFinishIsNull() {
            addCriterion("gmt_plan_finish is null");
            return (Criteria) this;
        }

        public Criteria andGmtPlanFinishIsNotNull() {
            addCriterion("gmt_plan_finish is not null");
            return (Criteria) this;
        }

        public Criteria andGmtPlanFinishEqualTo(Date value) {
            addCriterion("gmt_plan_finish =", value, "gmtPlanFinish");
            return (Criteria) this;
        }

        public Criteria andGmtPlanFinishNotEqualTo(Date value) {
            addCriterion("gmt_plan_finish <>", value, "gmtPlanFinish");
            return (Criteria) this;
        }

        public Criteria andGmtPlanFinishGreaterThan(Date value) {
            addCriterion("gmt_plan_finish >", value, "gmtPlanFinish");
            return (Criteria) this;
        }

        public Criteria andGmtPlanFinishGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_plan_finish >=", value, "gmtPlanFinish");
            return (Criteria) this;
        }

        public Criteria andGmtPlanFinishLessThan(Date value) {
            addCriterion("gmt_plan_finish <", value, "gmtPlanFinish");
            return (Criteria) this;
        }

        public Criteria andGmtPlanFinishLessThanOrEqualTo(Date value) {
            addCriterion("gmt_plan_finish <=", value, "gmtPlanFinish");
            return (Criteria) this;
        }

        public Criteria andGmtPlanFinishIn(List<Date> values) {
            addCriterion("gmt_plan_finish in", values, "gmtPlanFinish");
            return (Criteria) this;
        }

        public Criteria andGmtPlanFinishNotIn(List<Date> values) {
            addCriterion("gmt_plan_finish not in", values, "gmtPlanFinish");
            return (Criteria) this;
        }

        public Criteria andGmtPlanFinishBetween(Date value1, Date value2) {
            addCriterion("gmt_plan_finish between", value1, value2, "gmtPlanFinish");
            return (Criteria) this;
        }

        public Criteria andGmtPlanFinishNotBetween(Date value1, Date value2) {
            addCriterion("gmt_plan_finish not between", value1, value2, "gmtPlanFinish");
            return (Criteria) this;
        }

        public Criteria andGmtEndIsNull() {
            addCriterion("gmt_end is null");
            return (Criteria) this;
        }

        public Criteria andGmtEndIsNotNull() {
            addCriterion("gmt_end is not null");
            return (Criteria) this;
        }

        public Criteria andGmtEndEqualTo(Date value) {
            addCriterion("gmt_end =", value, "gmtEnd");
            return (Criteria) this;
        }

        public Criteria andGmtEndNotEqualTo(Date value) {
            addCriterion("gmt_end <>", value, "gmtEnd");
            return (Criteria) this;
        }

        public Criteria andGmtEndGreaterThan(Date value) {
            addCriterion("gmt_end >", value, "gmtEnd");
            return (Criteria) this;
        }

        public Criteria andGmtEndGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_end >=", value, "gmtEnd");
            return (Criteria) this;
        }

        public Criteria andGmtEndLessThan(Date value) {
            addCriterion("gmt_end <", value, "gmtEnd");
            return (Criteria) this;
        }

        public Criteria andGmtEndLessThanOrEqualTo(Date value) {
            addCriterion("gmt_end <=", value, "gmtEnd");
            return (Criteria) this;
        }

        public Criteria andGmtEndIn(List<Date> values) {
            addCriterion("gmt_end in", values, "gmtEnd");
            return (Criteria) this;
        }

        public Criteria andGmtEndNotIn(List<Date> values) {
            addCriterion("gmt_end not in", values, "gmtEnd");
            return (Criteria) this;
        }

        public Criteria andGmtEndBetween(Date value1, Date value2) {
            addCriterion("gmt_end between", value1, value2, "gmtEnd");
            return (Criteria) this;
        }

        public Criteria andGmtEndNotBetween(Date value1, Date value2) {
            addCriterion("gmt_end not between", value1, value2, "gmtEnd");
            return (Criteria) this;
        }

        public Criteria andEntityIdIsNull() {
            addCriterion("entity_id is null");
            return (Criteria) this;
        }

        public Criteria andEntityIdIsNotNull() {
            addCriterion("entity_id is not null");
            return (Criteria) this;
        }

        public Criteria andEntityIdEqualTo(Integer value) {
            addCriterion("entity_id =", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotEqualTo(Integer value) {
            addCriterion("entity_id <>", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdGreaterThan(Integer value) {
            addCriterion("entity_id >", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("entity_id >=", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdLessThan(Integer value) {
            addCriterion("entity_id <", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdLessThanOrEqualTo(Integer value) {
            addCriterion("entity_id <=", value, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdIn(List<Integer> values) {
            addCriterion("entity_id in", values, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotIn(List<Integer> values) {
            addCriterion("entity_id not in", values, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdBetween(Integer value1, Integer value2) {
            addCriterion("entity_id between", value1, value2, "entityId");
            return (Criteria) this;
        }

        public Criteria andEntityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("entity_id not between", value1, value2, "entityId");
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