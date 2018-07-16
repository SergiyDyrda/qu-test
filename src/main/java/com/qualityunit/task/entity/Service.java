package com.qualityunit.task.entity;

/**
 * Created by Sergiy Dyrda on 13.07.2018
 */
public class Service extends FieldOfRecord {

    private String variationId;

    public Service(String serviceId, String variationId) {
        super(serviceId);
        this.variationId = variationId;
    }

    public Service() {

    }

    public String getVariationId() {
        return variationId;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        if (!super.equals(o)) return false;

        Service service = (Service) o;

        return variationId == null || variationId.equals(service.variationId);
    }

    public static class AnyService extends Service {

        public AnyService() {
            super("*", "*");
        }

        @Override
        public boolean equals(Object o) {
            return o != null && o instanceof Service;
        }

    }
}
