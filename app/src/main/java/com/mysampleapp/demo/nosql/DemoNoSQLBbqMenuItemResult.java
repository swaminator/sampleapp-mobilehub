package com.mysampleapp.demo.nosql;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amazonaws.AmazonClientException;
import com.amazonaws.mobile.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.models.nosql.BbqMenuItemDO;

import java.util.Set;

public class DemoNoSQLBbqMenuItemResult implements DemoNoSQLResult {
    private static final int KEY_TEXT_COLOR = 0xFF333333;
    private final BbqMenuItemDO result;

    DemoNoSQLBbqMenuItemResult(final BbqMenuItemDO result) {
        this.result = result;
    }
    @Override
    public void updateItem() {
        final DynamoDBMapper mapper = AWSMobileClient.defaultMobileClient().getDynamoDBMapper();
    }

    @Override
    public void deleteItem() {
        final DynamoDBMapper mapper = AWSMobileClient.defaultMobileClient().getDynamoDBMapper();
        mapper.delete(result);
    }

    private void setKeyTextViewStyle(final TextView textView) {
        textView.setTextColor(KEY_TEXT_COLOR);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(dp(5), dp(2), dp(5), 0);
        textView.setLayoutParams(layoutParams);
    }

    /**
     * @param dp number of design pixels.
     * @return number of pixels corresponding to the desired design pixels.
     */
    private int dp(int dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
    private void setValueTextViewStyle(final TextView textView) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(dp(15), 0, dp(15), dp(2));
        textView.setLayoutParams(layoutParams);
    }

    private void setKeyAndValueTextViewStyles(final TextView keyTextView, final TextView valueTextView) {
        setKeyTextViewStyle(keyTextView);
        setValueTextViewStyle(valueTextView);
    }

    private static String bytesToHexString(byte[] bytes) {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("%02X", bytes[0]));
        for(int index = 1; index < bytes.length; index++) {
            builder.append(String.format(" %02X", bytes[index]));
        }
        return builder.toString();
    }

    private static String byteSetsToHexStrings(Set<byte[]> bytesSet) {
        final StringBuilder builder = new StringBuilder();
        int index = 0;
        for (byte[] bytes : bytesSet) {
            builder.append(String.format("%d: ", ++index));
            builder.append(bytesToHexString(bytes));
            if (index < bytesSet.size()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public View getView(final Context context, final View convertView, int position) {
        final LinearLayout layout;
        final TextView resultNumberTextView;
        final TextView restaurantIdKeyTextView;
        final TextView restaurantIdValueTextView;
        final TextView idKeyTextView;
        final TextView idValueTextView;
        if (convertView == null) {
            layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            resultNumberTextView = new TextView(context);
            resultNumberTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            layout.addView(resultNumberTextView);


            restaurantIdKeyTextView = new TextView(context);
            restaurantIdValueTextView = new TextView(context);
            setKeyAndValueTextViewStyles(restaurantIdKeyTextView, restaurantIdValueTextView);
            layout.addView(restaurantIdKeyTextView);
            layout.addView(restaurantIdValueTextView);

            idKeyTextView = new TextView(context);
            idValueTextView = new TextView(context);
            setKeyAndValueTextViewStyles(idKeyTextView, idValueTextView);
            layout.addView(idKeyTextView);
            layout.addView(idValueTextView);
        } else {
            layout = (LinearLayout) convertView;
            resultNumberTextView = (TextView) layout.getChildAt(0);

            restaurantIdKeyTextView = (TextView) layout.getChildAt(1);
            restaurantIdValueTextView = (TextView) layout.getChildAt(2);

            idKeyTextView = (TextView) layout.getChildAt(3);
            idValueTextView = (TextView) layout.getChildAt(4);
        }

        resultNumberTextView.setText(String.format("#%d", + position+1));
        restaurantIdKeyTextView.setText("restaurant_id");
        restaurantIdValueTextView.setText(result.getRestaurantId());
        idKeyTextView.setText("id");
        idValueTextView.setText(result.getId());
        return layout;
    }
}
