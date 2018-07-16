package com.qualityunit.task.entity;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */

public class Question extends FieldOfRecord {

    private String categoryId, subcategoryId;

    public Question(String questionTypeId, String categoryId, String subcategoryId) {
        super(questionTypeId);
        this.categoryId = categoryId;
        this.subcategoryId = subcategoryId;
    }

    public Question() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(String subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        if (!super.equals(o)) return false;

        Question question = (Question) o;

        return (categoryId == null || categoryId.equals(question.categoryId))
                && (subcategoryId == null || subcategoryId.equals(question.subcategoryId));
    }

    public static class AnyQuestion extends Question {

        public AnyQuestion() {
            super("*", "*", "*");
        }

        @Override
        public boolean equals(Object o) {
            return o != null && o instanceof Question;
        }
    }
}
