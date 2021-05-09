package com.am.steammarketanalyzer;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Item implements Parcelable {

    protected Item(Parcel in) {
        json = in.readString();
        id = in.readLong();
        imageLink = in.readString();
        name = in.readString();
        link = in.readString();
        state = in.readByte() != 0;
        switches = in.createBooleanArray();
        sellRange = in.createFloatArray();
        buyRange = in.createFloatArray();
        differenceRange = in.createFloatArray();
        sellDifferenceRange = in.createFloatArray();
        buyDifferenceRange = in.createFloatArray();
        sellAmountRange = in.createIntArray();
        buyAmountRange = in.createIntArray();
        amountDifferenceRange = in.createFloatArray();
        currency = in.readInt();
        refresh = in.readInt();
        refreshText = in.readString();
        created = in.readByte() != 0;
        response = in.readByte() != 0;
        sell = in.readFloat();
        sellNext = in.readFloat();
        sellAmount = in.readInt();
        buy = in.readFloat();
        buyNext = in.readFloat();
        buyAmount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(json);
        dest.writeLong(id);
        dest.writeString(imageLink);
        dest.writeString(name);
        dest.writeString(link);
        dest.writeByte((byte) (state ? 1 : 0));
        dest.writeBooleanArray(switches);
        dest.writeFloatArray(sellRange);
        dest.writeFloatArray(buyRange);
        dest.writeFloatArray(differenceRange);
        dest.writeFloatArray(sellDifferenceRange);
        dest.writeFloatArray(buyDifferenceRange);
        dest.writeIntArray(sellAmountRange);
        dest.writeIntArray(buyAmountRange);
        dest.writeFloatArray(amountDifferenceRange);
        dest.writeInt(currency);
        dest.writeInt(refresh);
        dest.writeString(refreshText);
        dest.writeByte((byte) (created ? 1 : 0));
        dest.writeByte((byte) (response ? 1 : 0));
        dest.writeFloat(sell);
        dest.writeFloat(sellNext);
        dest.writeInt(sellAmount);
        dest.writeFloat(buy);
        dest.writeFloat(buyNext);
        dest.writeInt(buyAmount);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() { return 0; }


    private final String json;
    public String getJson() {
        return json;
    }

    private final long id;
    public long getId() { return id; }


    private final String imageLink;
    public String getImageLink() {
        return imageLink;
    }

    private final String name;
    public String getName() {
        return name;
    }

    private final String link;
    public String getLink() {
        return link;
    }

    private boolean state;
    public boolean getState() {
        return state;
    }
    public void setState(boolean newValue) {
        state = newValue;
    }


    private boolean[] switches = new boolean[8];
    public boolean[] getSwitches() {
        return switches;
    }
    public void setSwitch(int index, boolean newValue) { switches[index] = newValue; }


    private float[] sellRange = new float[2];
    public float[] getSellRange() {
        return sellRange;
    }
    public void setSellRange(int index, float newValue) { sellRange[index] = newValue; }

    private float[] buyRange = new float[2];
    public float[] getBuyRange() {
        return buyRange;
    }
    public void setBuyRange(int index, float newValue) { buyRange[index] = newValue; }

    private float[] differenceRange = new float[2];
    public float[] getDifferenceRange() {
        return differenceRange;
    }
    public void setDifferenceRange(int index, float newValue) { differenceRange[index] = newValue; }

    private float[] sellDifferenceRange = new float[2];
    public float[] getSellDifferenceRange() {
        return sellDifferenceRange;
    }
    public void setSellDifferenceRange(int index, float newValue) { sellDifferenceRange[index] = newValue; }

    private float[] buyDifferenceRange = new float[2];
    public float[] getBuyDifferenceRange() {
        return buyDifferenceRange;
    }
    public void setBuyDifferenceRange(int index, float newValue) { buyDifferenceRange[index] = newValue; }

    private int[] sellAmountRange = new int[2];
    public int[] getSellAmountRange() {
        return sellAmountRange;
    }
    public void setSellAmountRange(int index, int newValue) { sellAmountRange[index] = newValue; }

    private int[] buyAmountRange = new int[2];
    public int[] getBuyAmountRange() {
        return buyAmountRange;
    }
    public void setBuyAmountRange(int index, int newValue) { buyAmountRange[index] = newValue; }

    private float[] amountDifferenceRange = new float[2];
    public float[] getAmountDifferenceRange() {
        return amountDifferenceRange;
    }
    public void setAmountDifferenceRange(int index, float newValue) { amountDifferenceRange[index] = newValue; }


    private int currency = 1;
    public int getCurrency() {
        return currency;
    }
    public void setCurrency(int newValue) {
        currency = newValue;
    }

    private int refresh = 30;
    public int getRefresh() {
        return refresh;
    }
    public void setRefresh(int newValue) {
        refresh = newValue;
    }

    private String refreshText = "0:0:0:30";
    public String getRefreshText() {
        return refreshText;
    }
    public void setRefreshText(String newValue) {
        refreshText = newValue;
    }


    private boolean created;
    public boolean getCreated() {
        return created;
    }
    public void setCreated(boolean newValue) {
        created = newValue;
    }


    private boolean response;
    public boolean getResponse() {
        return response;
    }
    public void setResponse(boolean newValue) {
        response = newValue;
    }

    private float sell;
    public float getSell() { return sell; }
    public void setSell(float newValue) { sell = newValue; }

    private float sellNext;
    public float getSellNext() { return sellNext; }
    public void setSellNext(float newValue) { sellNext = newValue; }

    private int sellAmount;
    public int getSellAmount() { return sellAmount; }
    public void setSellAmount(int newValue) { sellAmount = newValue; }

    private float buy;
    public float getBuy() { return buy; }
    public void setBuy(float newValue) { buy = newValue; }

    private float buyNext;
    public float getBuyNext() { return buyNext; }
    public void setBuyNext(float newValue) { buyNext = newValue; }

    private int buyAmount;
    public int getBuyAmount() { return buyAmount; }
    public void setBuyAmount(int newValue) { buyAmount = newValue; }


    public String difference(int value1, int value2) { return String.valueOf(Math.abs(value1 - value2)); }

    /*
    @SuppressLint("DefaultLocale")
    public String difference(float value1, float value2) { return String.format("%.2f", Math.abs(value1 - value2)); }
    */

    @SuppressLint("DefaultLocale")
    public String difference(String value1, String value2) {
            String match = findFloat(value1);

            float floatValue1 = Float.parseFloat(match);
            float floatValue2 = Float.parseFloat(findFloat(value2));
            float result = Math.abs(floatValue1 - floatValue2);

            return value1.replace(match, String.format("%.2f", result));
    }

    @SuppressLint("DefaultLocale")
    public String multiplier(int value1, int value2) { return String.format("%.2f", value1 > value2 ? (float) value1 / value2 : (float) value2 / value1); }

    @SuppressLint("DefaultLocale")
    public String multiplier(float value1, float value2) { return String.format("%.2f", value1 > value2 ? value1 / value2 : value2 / value1); }

    /*
    @SuppressLint("DefaultLocale")
    public String multiplier(String value1, String value2) {
        String match = findFloat(value1);

        float floatValue1 = Float.parseFloat(match);
        float floatValue2 = Float.parseFloat(findFloat(value2));
        float result = floatValue1 > floatValue2 ? floatValue1 / floatValue2 : floatValue2 / floatValue1;

        return value1.replace(match, String.format("%.2f", result));
    }
    */

    public String findFloat(String value) {
        Pattern pattern = Pattern.compile("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)");
        Matcher matcher = pattern.matcher(value);

        if (matcher.find())
            return matcher.group(0);

        return "";
    }

    public HashMap<String, String> getValuesFromJson(JsonObject json) {
        HashMap<String, String> values = putValuesToHashMap(json, "sell");
        values.putAll(putValuesToHashMap(json, "buy"));

        return values;
    }

    private HashMap<String, String> putValuesToHashMap(JsonObject json, String action) {
        HashMap<String, String> values = new HashMap<>();

        if (json.get(action + "_order_summary").toString().equals("There are no active listings for this item.")) {
            values.put(action, "");
            values.put(action + "Next", "");
            values.put(action + "Amount", "");
        }
        else {
            values.put(action + "Amount", getValueFromHtml(json.get(action + "_order_summary"), "span", 0));
            if (values.get(action + "Amount").equals("1")) {
                values.put(action, "");
                values.put(action + "Next", "");
            }
            else {
                values.put(action, getValueFromHtml(json.get(action + "_order_summary"), "span", 1).replace(",", "."));
                values.put(action + "Next", getValueFromHtml(json.get(action + "_order_table"), "td", 2).replace(",", "."));
            }
        }

        return values;
    }

    private String getValueFromHtml(com.google.gson.JsonElement respond, String element, int index) {
        Document doc = Jsoup.parse(respond.toString().replace("\\", ""));
        Element link = doc.select(element).get(index);
        return link.text();
    }


    @SuppressLint("DefaultLocale")
    public Item(long id, String imageLink, String name, String link) {
        this.id = id;

        this.imageLink = imageLink;
        this.name = name;
        this.link = link;

        json = String.format("https://steamcommunity.com/market/itemordershistogram?language=english&item_nameid=%d&currency=", id);
    }
}