package com.amazonaws.models.nosql;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "reactsample-mobilehub-2110989342-bbq_menu_item")

public class BbqMenuItemDO {
    private String _restaurantId;
    private String _id;

    @DynamoDBHashKey(attributeName = "restaurant_id")
    @DynamoDBAttribute(attributeName = "restaurant_id")
    public String getRestaurantId() {
        return _restaurantId;
    }

    public void setRestaurantId(final String _restaurantId) {
        this._restaurantId = _restaurantId;
    }
    @DynamoDBRangeKey(attributeName = "id")
    @DynamoDBAttribute(attributeName = "id")
    public String getId() {
        return _id;
    }

    public void setId(final String _id) {
        this._id = _id;
    }

}
