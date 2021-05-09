package com.am.steammarketanalyzer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageButton;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.HashMap;

public class ActivityItem extends AppCompatActivity {

    HashMap<String, Object> internetValues = new HashMap<>();
    private Item item;

    private ImageButton stateView;
    TextView titleView, subtitleView,
             sellView, sellValueView,
             buyView, buyValueView,
             differenceView, differenceSwitchView,
             differenceValueView, sellDifferenceView,
             sellDifferenceSwitchView, sellDifferenceValueView,
             buyDifferenceView, buyDifferenceSwitchView,
             buyDifferenceValueView, sellAmountView,
             sellAmountValueView, buyAmountView,
             buyAmountValueView, amountDifferenceView,
             amountDifferenceSwitchView, amountDifferenceValueView,
             sellRangeView, sellRangeMinView, sellRangeMaxView,
             buyRangeView, buyRangeMinView, buyRangeMaxView,
             differenceRangeView, differenceRangeSwitchView,
             differenceRangeMinView, differenceRangeMaxView,
             sellDifferenceRangeView, sellDifferenceRangeSwitchView,
             sellDifferenceRangeMinView, sellDifferenceRangeMaxView,
             buyDifferenceRangeView, buyDifferenceRangeSwitchView,
             buyDifferenceRangeMinView, buyDifferenceRangeMaxView,
             sellAmountRangeView, sellAmountRangeMinView,
             sellAmountRangeMaxView, buyAmountRangeView,
             buyAmountRangeMinView, buyAmountRangeMaxView,
             amountDifferenceRangeView, amountDifferenceRangeSwitchView,
             amountDifferenceRangeMinView, amountDifferenceRangeMaxView,
             currencyView, currencyValueView, refreshView, refreshValueView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        item = getIntent().getParcelableExtra(getString(R.string.e_item));
        findViews();

        loadSettings();
        loadValues();
        loadListeners();
        updateFromInternet();
    }

    @Override
    public void onDestroy() { super.onDestroy(); }

    private void findViews() {
        stateView = findViewById(R.id.state);

        titleView = findViewById(R.id.title);
        subtitleView = findViewById(R.id.subtitle);

        sellView = findViewById(R.id.sell);
        sellValueView = findViewById(R.id.sellValue);

        buyView = findViewById(R.id.buy);
        buyValueView = findViewById(R.id.buyValue);

        differenceView = findViewById(R.id.difference);
        differenceSwitchView = findViewById(R.id.differenceSwitch);
        differenceValueView = findViewById(R.id.differenceValue);

        sellDifferenceView = findViewById(R.id.sellDifference);
        sellDifferenceSwitchView = findViewById(R.id.sellDifferenceSwitch);
        sellDifferenceValueView = findViewById(R.id.sellDifferenceValue);

        buyDifferenceView = findViewById(R.id.buyDifference);
        buyDifferenceSwitchView = findViewById(R.id.buyDifferenceSwitch);
        buyDifferenceValueView = findViewById(R.id.buyDifferenceValue);

        sellAmountView = findViewById(R.id.sellAmount);
        sellAmountValueView = findViewById(R.id.sellAmountValue);

        buyAmountView = findViewById(R.id.buyAmount);
        buyAmountValueView = findViewById(R.id.buyAmountValue);

        amountDifferenceView = findViewById(R.id.amountDifference);
        amountDifferenceSwitchView = findViewById(R.id.amountDifferenceSwitch);
        amountDifferenceValueView = findViewById(R.id.amountDifferenceValue);

        sellRangeView = findViewById(R.id.sellRange);
        sellRangeMinView = findViewById(R.id.sellRangeMin);
        sellRangeMaxView = findViewById(R.id.sellRangeMax);

        buyRangeView = findViewById(R.id.buyRange);
        buyRangeMinView = findViewById(R.id.buyRangeMin);
        buyRangeMaxView = findViewById(R.id.buyRangeMax);

        differenceRangeView = findViewById(R.id.differenceRange);
        differenceRangeSwitchView = findViewById(R.id.differenceRangeSwitch);
        differenceRangeMinView = findViewById(R.id.differenceRangeMin);
        differenceRangeMaxView = findViewById(R.id.differenceRangeMax);

        sellDifferenceRangeView = findViewById(R.id.sellDifferenceRange);
        sellDifferenceRangeSwitchView = findViewById(R.id.sellDifferenceRangeSwitch);
        sellDifferenceRangeMinView = findViewById(R.id.sellDifferenceRangeMin);
        sellDifferenceRangeMaxView = findViewById(R.id.sellDifferenceRangeMax);

        buyDifferenceRangeView = findViewById(R.id.buyDifferenceRange);
        buyDifferenceRangeSwitchView = findViewById(R.id.buyDifferenceRangeSwitch);
        buyDifferenceRangeMinView = findViewById(R.id.buyDifferenceRangeMin);
        buyDifferenceRangeMaxView = findViewById(R.id.buyDifferenceRangeMax);

        sellAmountRangeView = findViewById(R.id.sellAmountRange);
        sellAmountRangeMinView = findViewById(R.id.sellAmountRangeMin);
        sellAmountRangeMaxView = findViewById(R.id.sellAmountRangeMax);

        buyAmountRangeView = findViewById(R.id.buyAmountRange);
        buyAmountRangeMinView = findViewById(R.id.buyAmountRangeMin);
        buyAmountRangeMaxView = findViewById(R.id.buyAmountRangeMax);

        amountDifferenceRangeView = findViewById(R.id.amountDifferenceRange);
        amountDifferenceRangeSwitchView = findViewById(R.id.amountDifferenceRangeSwitch);
        amountDifferenceRangeMinView = findViewById(R.id.amountDifferenceRangeMin);
        amountDifferenceRangeMaxView = findViewById(R.id.amountDifferenceRangeMax);

        currencyView = findViewById(R.id.currency);
        currencyValueView = findViewById(R.id.currencyValue);

        refreshView = findViewById(R.id.refresh);
        refreshValueView = findViewById(R.id.refreshValue);
    }

    private void loadSettings() {
        sellView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sell")[1], DialogTranslation.getValues("sell")[0]));
        buyView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buy")[1], DialogTranslation.getValues("buy")[0]));

        differenceView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("difference")[1], DialogTranslation.getValues("difference")[0]));
        differenceSwitchView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("differenceSwitch")[1], DialogTranslation.getValues("differenceSwitch")[0]));

        sellDifferenceView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sellDifference")[1], DialogTranslation.getValues("sellDifference")[0]));
        sellDifferenceSwitchView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sellDifferenceSwitch")[1], DialogTranslation.getValues("sellDifferenceSwitch")[0]));

        buyDifferenceView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buyDifference")[1], DialogTranslation.getValues("buyDifference")[0]));
        buyDifferenceSwitchView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buyDifferenceSwitch")[1], DialogTranslation.getValues("buyDifferenceSwitch")[0]));

        sellAmountView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sellAmount")[1], DialogTranslation.getValues("sellAmount")[0]));
        buyAmountView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buyAmount")[1], DialogTranslation.getValues("buyAmount")[0]));

        amountDifferenceView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("amountDifference")[1], DialogTranslation.getValues("amountDifference")[0]));
        amountDifferenceSwitchView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("amountDifferenceSwitch")[1], DialogTranslation.getValues("amountDifferenceSwitch")[0]));

        sellRangeView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sellRange")[1], DialogTranslation.getValues("sellRange")[0]));
        sellRangeMinView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sellRangeMin")[1], DialogTranslation.getValues("sellRangeMin")[0]));
        sellRangeMaxView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sellRangeMax")[1], DialogTranslation.getValues("sellRangeMax")[0]));

        buyRangeView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buyRange")[1], DialogTranslation.getValues("buyRange")[0]));
        buyRangeMinView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buyRangeMin")[1], DialogTranslation.getValues("buyRangeMin")[0]));
        buyRangeMaxView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buyRangeMax")[1], DialogTranslation.getValues("buyRangeMax")[0]));

        differenceRangeView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("differenceRange")[1], DialogTranslation.getValues("differenceRange")[0]));
        differenceRangeSwitchView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("differenceRangeSwitch")[1], DialogTranslation.getValues("differenceRangeSwitch")[0]));
        differenceRangeMinView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("differenceRangeMin")[1], DialogTranslation.getValues("differenceRangeMin")[0]));
        differenceRangeMaxView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("differenceRangeMax")[1], DialogTranslation.getValues("differenceRangeMax")[0]));

        sellDifferenceRangeView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sellDifferenceRange")[1], DialogTranslation.getValues("sellDifferenceRange")[0]));
        sellDifferenceRangeSwitchView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sellDifferenceRangeSwitch")[1], DialogTranslation.getValues("sellDifferenceRangeSwitch")[0]));
        sellDifferenceRangeMinView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sellDifferenceRangeMin")[1], DialogTranslation.getValues("sellDifferenceRangeMin")[0]));
        sellDifferenceRangeMaxView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sellDifferenceRangeMax")[1], DialogTranslation.getValues("sellDifferenceRangeMax")[0]));

        buyDifferenceRangeView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buyDifferenceRange")[1], DialogTranslation.getValues("buyDifferenceRange")[0]));
        buyDifferenceRangeSwitchView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buyDifferenceRangeSwitch")[1], DialogTranslation.getValues("buyDifferenceRangeSwitch")[0]));
        buyDifferenceRangeMinView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buyDifferenceRangeMin")[1], DialogTranslation.getValues("buyDifferenceRangeMin")[0]));
        buyDifferenceRangeMaxView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buyDifferenceRangeMax")[1], DialogTranslation.getValues("buyDifferenceRangeMax")[0]));

        sellAmountRangeView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sellAmountRange")[1], DialogTranslation.getValues("sellAmountRange")[0]));
        sellAmountRangeMinView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sellAmountRangeMin")[1], DialogTranslation.getValues("sellAmountRangeMin")[0]));
        sellAmountRangeMaxView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("sellAmountRangeMax")[1], DialogTranslation.getValues("sellAmountRangeMax")[0]));

        buyAmountRangeView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buyAmountRange")[1], DialogTranslation.getValues("buyAmountRange")[0]));
        buyAmountRangeMinView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buyAmountRangeMin")[1], DialogTranslation.getValues("buyAmountRangeMin")[0]));
        buyAmountRangeMaxView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("buyAmountRangeMax")[1], DialogTranslation.getValues("buyAmountRangeMax")[0]));

        amountDifferenceRangeView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("amountDifferenceRange")[1], DialogTranslation.getValues("amountDifferenceRange")[0]));
        amountDifferenceRangeSwitchView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("amountDifferenceRangeSwitch")[1], DialogTranslation.getValues("amountDifferenceRangeSwitch")[0]));
        amountDifferenceRangeMinView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("amountDifferenceRangeMin")[1], DialogTranslation.getValues("amountDifferenceRangeMin")[0]));
        amountDifferenceRangeMaxView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("amountDifferenceRangeMax")[1], DialogTranslation.getValues("amountDifferenceRangeMax")[0]));

        currencyView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("currency")[1], DialogTranslation.getValues("currency")[0]));
        currencyValueView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("currencyValue")[1], DialogTranslation.getValues("currencyValue")[0]));

        refreshView.setText(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("refresh")[1], DialogTranslation.getValues("refresh")[0]));
        refreshValueView.setHint(ActivityMain.getSharedPreferences(this).getString(DialogTranslation.getValues("refreshValue")[1], DialogTranslation.getValues("refreshValue")[0]));
    }

    @SuppressLint("DefaultLocale")
    private void loadValues() {
        stateView.setColorFilter(item.getState() ? getColor(R.color.white) : getColor(R.color.steam_4));

        titleView.setText(item.getName());
        subtitleView.setText(item.getLink());

        differenceSwitchView.setTextColor(item.getSwitches()[0] ? getColor(R.color.white) : getColor(R.color.steam_4));
        sellDifferenceSwitchView.setTextColor(item.getSwitches()[1] ? getColor(R.color.white) : getColor(R.color.steam_4));
        buyDifferenceSwitchView.setTextColor(item.getSwitches()[2] ? getColor(R.color.white) : getColor(R.color.steam_4));
        amountDifferenceSwitchView.setTextColor(item.getSwitches()[3] ? getColor(R.color.white) : getColor(R.color.steam_4));

        differenceRangeSwitchView.setTextColor(item.getSwitches()[4] ? getColor(R.color.white) : getColor(R.color.steam_4));
        sellDifferenceRangeSwitchView.setTextColor(item.getSwitches()[5] ? getColor(R.color.white) : getColor(R.color.steam_4));
        buyDifferenceRangeSwitchView.setTextColor(item.getSwitches()[6] ? getColor(R.color.white) : getColor(R.color.steam_4));
        amountDifferenceRangeSwitchView.setTextColor(item.getSwitches()[7] ? getColor(R.color.white) : getColor(R.color.steam_4));

        if (!item.getCreated()) { item.setCreated(true); return; }

        sellRangeMinView.setText(String.valueOf(item.getSellRange()[0]));
        sellRangeMaxView.setText(String.valueOf(item.getSellRange()[1]));

        buyRangeMinView.setText(String.valueOf(item.getBuyRange()[0]));
        buyRangeMaxView.setText(String.valueOf(item.getBuyRange()[1]));

        differenceRangeMinView.setText(String.valueOf(item.getDifferenceRange()[0]));
        differenceRangeMaxView.setText(String.valueOf(item.getDifferenceRange()[1]));

        sellDifferenceRangeMinView.setText(String.valueOf(item.getSellDifferenceRange()[0]));
        sellDifferenceRangeMaxView.setText(String.valueOf(item.getSellDifferenceRange()[1]));

        buyDifferenceRangeMinView.setText(String.valueOf(item.getBuyDifferenceRange()[0]));
        buyDifferenceRangeMaxView.setText(String.valueOf(item.getBuyDifferenceRange()[1]));

        sellAmountRangeMinView.setText(String.valueOf(item.getSellAmountRange()[0]));
        sellAmountRangeMaxView.setText(String.valueOf(item.getSellAmountRange()[1]));

        buyAmountRangeMinView.setText(String.valueOf(item.getBuyAmountRange()[0]));
        buyAmountRangeMaxView.setText(String.valueOf(item.getBuyAmountRange()[1]));

        if (item.getSwitches()[7]) {
            amountDifferenceRangeMinView.setText(String.format("%.2f", item.getAmountDifferenceRange()[0]));
            amountDifferenceRangeMaxView.setText(String.format("%.2f", item.getAmountDifferenceRange()[1]));
        }
        else {
            amountDifferenceRangeMinView.setText(String.format("%.0f", item.getAmountDifferenceRange()[0]));
            amountDifferenceRangeMaxView.setText(String.format("%.0f", item.getAmountDifferenceRange()[1]));
        }

        currencyValueView.setText(String.valueOf(item.getCurrency()));
        refreshValueView.setText(item.getRefreshText());
    }

    private void loadListeners() {
        stateView.setOnClickListener(v -> switchState());

        differenceSwitchView.setOnClickListener(v -> callSwitchFunction((TextView) v));
        sellDifferenceSwitchView.setOnClickListener(v -> callSwitchFunction((TextView) v));
        buyDifferenceSwitchView.setOnClickListener(v -> callSwitchFunction((TextView) v));
        amountDifferenceSwitchView.setOnClickListener(v -> callSwitchFunction((TextView) v));

        differenceRangeSwitchView.setOnClickListener(v -> callSwitchFunction((TextView) v));
        sellDifferenceRangeSwitchView.setOnClickListener(v -> callSwitchFunction((TextView) v));
        buyDifferenceRangeSwitchView.setOnClickListener(v -> callSwitchFunction((TextView) v));
        amountDifferenceRangeSwitchView.setOnClickListener(v -> callSwitchFunction((TextView) v));

        sellRangeMinView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));
        sellRangeMaxView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));

        buyRangeMinView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));
        buyRangeMaxView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));

        differenceRangeMinView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));
        differenceRangeMaxView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));

        sellDifferenceRangeMinView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));
        sellDifferenceRangeMaxView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));

        buyDifferenceRangeMinView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));
        buyDifferenceRangeMaxView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));

        sellAmountRangeMinView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));
        sellAmountRangeMaxView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));

        buyAmountRangeMinView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));
        buyAmountRangeMaxView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));

        amountDifferenceRangeMinView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));
        amountDifferenceRangeMaxView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));

        refreshValueView.setOnFocusChangeListener((v, hasFocus) -> updateItem((TextView) v, hasFocus));

        currencyValueView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                item.setCurrency(intTryParse(currencyValueView.getText().toString(), 1));
                if (item.getCurrency() < 42)
                    updateFromInternet();
            }
        });
    }

    private void updateFromInternet() {
        Ion.with(this).load(item.getJson() + item.getCurrency()).asJsonObject().setCallback((e, result) -> {
            HashMap<String, String> values = item.getValuesFromJson(result);

            internetValues.put("sellNextString", values.get("sellNext"));
            internetValues.put("buyNextString", values.get("buyNext"));

            internetValues.put("sell", Float.parseFloat(item.findFloat(values.get("sell"))));
            internetValues.put("sellNext", Float.parseFloat(item.findFloat(values.get("sellNext"))));
            internetValues.put("sellAmount", Integer.parseInt(values.get("sellAmount")));

            internetValues.put("buy", Float.parseFloat(item.findFloat(values.get("buy"))));
            internetValues.put("buyNext", Float.parseFloat(item.findFloat(values.get("buyNext"))));
            internetValues.put("buyAmount", Integer.parseInt(values.get("buyAmount")));

            sellValueView.setText(values.get("sell"));
            buyValueView.setText(values.get("buy"));

            if (item.getSwitches()[0]) differenceValueView.setText(item.multiplier((float) internetValues.get("sell"), (float) internetValues.get("buy"))); else differenceValueView.setText(item.difference(values.get("sell"), values.get("buy")));
            if (item.getSwitches()[1]) sellDifferenceValueView.setText(item.multiplier((float) internetValues.get("sell"), (float) internetValues.get("sellNext"))); else sellDifferenceValueView.setText(item.difference(values.get("sell"), values.get("sellNext")));
            if (item.getSwitches()[2]) buyDifferenceValueView.setText(item.multiplier((float) internetValues.get("buy"), (float) internetValues.get("buyNext"))); else buyDifferenceValueView.setText(item.difference(values.get("buy"), values.get("buyNext")));

            sellAmountValueView.setText(values.get("sellAmount"));
            buyAmountValueView.setText(values.get("buyAmount"));

            if (item.getSwitches()[3]) amountDifferenceValueView.setText(item.multiplier((int) internetValues.get("sellAmount"), (int) internetValues.get("buyAmount"))); else amountDifferenceValueView.setText(item.difference((int) internetValues.get("sellAmount"), (int) internetValues.get("buyAmount")));

            item.setResponse(true);
        });
    }

    private void update() {
        if (!item.getResponse()) return;

        item.setSell((float) internetValues.get("sell"));
        item.setSellNext((float) internetValues.get("sellNext"));
        item.setSellAmount((int) internetValues.get("sellAmount"));

        item.setBuy((float) internetValues.get("buy"));
        item.setBuyNext((float) internetValues.get("buyNext"));
        item.setBuyAmount((int) internetValues.get("buyAmount"));

        item.setResponse(false);
    }

    @SuppressLint("DefaultLocale")
    private void callSwitchFunction(TextView textView) {
        int index = Integer.parseInt(textView.getTag().toString());

        item.setSwitch(index, !item.getSwitches()[index]);
        textView.setTextColor(item.getSwitches()[index] ? getColor(R.color.white) : getColor(R.color.steam_4));
        update();

        switch (index) {
            case 0:
                if (item.getSwitches()[index])
                    differenceValueView.setText(item.multiplier(item.getSell(), item.getBuy()));
                else
                    differenceValueView.setText(item.difference(sellValueView.getText().toString(), buyValueView.getText().toString()));
                break;
            case 1:
                if (item.getSwitches()[index])
                    sellDifferenceValueView.setText(item.multiplier(item.getSell(), item.getSellNext()));
                else
                    sellDifferenceValueView.setText(item.difference(sellValueView.getText().toString(), (String) internetValues.get("sellNextString")));
                break;
            case 2:
                if (item.getSwitches()[index])
                    buyDifferenceValueView.setText(item.multiplier(item.getBuy(), item.getBuyNext()));
                else
                    buyDifferenceValueView.setText(item.difference(buyValueView.getText().toString(), (String) internetValues.get("buyNextString")));
                break;
            case 3:
                if (item.getSwitches()[index])
                    amountDifferenceValueView.setText(item.multiplier(item.getSellAmount(), item.getBuyAmount()));
                else
                    amountDifferenceValueView.setText(item.difference(item.getSellAmount(), item.getBuyAmount()));
                break;
            case 7:
                if (item.getSwitches()[index]) {
                    amountDifferenceRangeMinView.setText(String.format("%.2f", item.getAmountDifferenceRange()[0]));
                    amountDifferenceRangeMaxView.setText(String.format("%.2f", item.getAmountDifferenceRange()[1]));
                }
                else {
                    amountDifferenceRangeMinView.setText(String.format("%.0f", item.getAmountDifferenceRange()[0]));
                    amountDifferenceRangeMaxView.setText(String.format("%.0f", item.getAmountDifferenceRange()[1]));
                }
                break;
            default:
                break;
        }
    }

    private void switchState() {
        item.setState(!item.getState());
        stateView.setColorFilter(item.getState() ? getColor(R.color.white) : getColor(R.color.steam_4));
    }

    private void updateItem(TextView textView, boolean hasFocus) {
        if (hasFocus)
            return;

        String fullName = getResources().getResourceName(textView.getId());
        String name = fullName.substring(fullName.lastIndexOf("/") + 1);

        switch (name) {
            case "sellRangeMin":
                item.setSellRange(0, floatTryParse(textView.getText().toString()));
                break;
            case "sellRangeMax":
                item.setSellRange(1, floatTryParse(textView.getText().toString()));
                break;
            case "buyRangeMin":
                item.setBuyRange(0, floatTryParse(textView.getText().toString()));
                break;
            case "buyRangeMax":
                item.setBuyRange(1, floatTryParse(textView.getText().toString()));
                break;
            case "differenceRangeMin":
                item.setDifferenceRange(0, floatTryParse(textView.getText().toString()));
                break;
            case "differenceRangeMax":
                item.setDifferenceRange(1, floatTryParse(textView.getText().toString()));
                break;
            case "sellDifferenceRangeMin":
                item.setSellDifferenceRange(0, floatTryParse(textView.getText().toString()));
                break;
            case "sellDifferenceRangeMax":
                item.setSellDifferenceRange(1, floatTryParse(textView.getText().toString()));
                break;
            case "buyDifferenceRangeMin":
                item.setBuyDifferenceRange(0, floatTryParse(textView.getText().toString()));
                break;
            case "buyDifferenceRangeMax":
                item.setBuyDifferenceRange(1, floatTryParse(textView.getText().toString()));
                break;
            case "sellAmountRangeMin":
                item.setSellAmountRange(0, intTryParse(textView.getText().toString()));
                break;
            case "sellAmountRangeMax":
                item.setSellAmountRange(1, intTryParse(textView.getText().toString()));
                break;
            case "buyAmountRangeMin":
                item.setBuyAmountRange(0, intTryParse(textView.getText().toString()));
                break;
            case "buyAmountRangeMax":
                item.setBuyAmountRange(1, intTryParse(textView.getText().toString()));
                break;
            case "amountDifferenceRangeMin":
                item.setAmountDifferenceRange(0, floatTryParse(textView.getText().toString()));
                break;
            case "amountDifferenceRangeMax":
                item.setAmountDifferenceRange(1, floatTryParse(textView.getText().toString()));
                break;
            case "refreshValue":
                String[] refresh = textView.getText().toString().split(":");
                item.setRefresh(intTryParse(refresh[refresh.length - 1]) + intTryParse(refresh[refresh.length - 2]) * 60 + intTryParse(refresh[refresh.length - 3]) * 3600 + intTryParse(refresh[refresh.length - 4]) * 86400);
                item.setRefreshText(textView.getText().toString());
                break;
            default:
                break;
        }
    }

    public float floatTryParse(String value, float defaultVal) {
        try { return Float.parseFloat(value); } catch (Exception e) { return defaultVal; }
    }

    public float floatTryParse(String value) {
        return floatTryParse(value, 0.0f);
    }

    public int intTryParse(String value, int defaultVal) {
        try { return Integer.parseInt(value); } catch (Exception e) { return defaultVal; }
    }

    public int intTryParse(String value) {
        return intTryParse(value, 0);
    }
}