package com.am.steammarketanalyzer;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DialogTranslation extends AppCompatDialogFragment implements AdapterView.OnItemSelectedListener {

    private Context context;

    private EditText editView;
    private String lastElement;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_translation, null);

        Spinner spinnerView = view.findViewById(R.id.translationSpinner);
        ImageButton resetView = view.findViewById(R.id.translationReset);
        editView = view.findViewById(R.id.translationEdit);

        context = Objects.requireNonNull(this.getActivity());
        loadSettings();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, new ArrayList<>(translations.keySet()));
        spinnerView.setAdapter(adapter);

        spinnerView.setOnItemSelectedListener(this);

        resetView.setOnLongClickListener(v -> showRestoreAlert());
        resetView.setOnClickListener(v -> loadTranslation(lastElement, true));

        builder.setView(view)
                .setPositiveButton(ActivityMain.getSharedPreferences(context).getString(getValues("translationOk")[1], getValues("translationOk")[0]), (dialogInterface, i) -> { });
        return builder.create();
    }

    @Override
    public void onDestroy() { super.onDestroy(); saveTranslation(lastElement); }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (lastElement != null)
            saveTranslation(lastElement);

        loadTranslation(parent.getItemAtPosition(position).toString(), false);
        lastElement = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    private boolean showRestoreAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(ActivityMain.getSharedPreferences(context).getString(getValues("restore")[1], getValues("restore")[0]));

        builder.setNegativeButton(ActivityMain.getSharedPreferences(context).getString(getValues("restoreCancel")[1], getValues("restoreCancel")[0]), (dialogInterface, i) -> { });
        builder.setPositiveButton(ActivityMain.getSharedPreferences(context).getString(getValues("restoreOk")[1], getValues("restoreOk")[0]), (dialogInterface, i) -> restoreAllTranslations());
        builder.create().show();

        return true;
    }

    private void restoreAllTranslations() {
        for (String[] element : translations.values())
            ActivityMain.getSharedPreferences(context).edit().putString(element[1], element[0]).apply();

        loadTranslation(lastElement, true);
    }

    private void saveTranslation(String element) { ActivityMain.getSharedPreferences(context).edit().putString(translations.get(element)[1], editView.getText().toString()).apply(); }

    private void loadTranslation(String element, boolean restoreTranslation) { editView.setText(restoreTranslation ? translations.get(element)[0] : ActivityMain.getSharedPreferences(context).getString(translations.get(element)[1], translations.get(element)[0])); }

    private void loadSettings() { editView.setHint(ActivityMain.getSharedPreferences(context).getString(getValues("translation")[1], getValues("translation")[0])); }

    static final Map<String, String[]> translations;
    static {
        translations = new LinkedHashMap<>();

        translations.put("Link (hint)", getValues("link"));
        translations.put("Name (hint)", getValues("name"));

        translations.put("Item not found (toast)", getValues("notFound"));

        translations.put("Sell: (text)", getValues("sell"));
        translations.put("Buy: (text)", getValues("buy"));

        translations.put("Difference: (text)", getValues("difference"));
        translations.put("x (text difference switch)", getValues("differenceSwitch"));

        translations.put("Sell difference: (text)", getValues("sellDifference"));
        translations.put("x (text sell difference switch)", getValues("sellDifferenceSwitch"));

        translations.put("Buy difference: (text)", getValues("buyDifference"));
        translations.put("x (text buy difference switch)", getValues("buyDifferenceSwitch"));

        translations.put("Sell amount: (text)", getValues("sellAmount"));
        translations.put("Buy amount: (text)", getValues("buyAmount"));

        translations.put("Amount difference: (text)", getValues("amountDifference"));
        translations.put("x (text amount difference switch)", getValues("amountDifferenceSwitch"));

        translations.put("Sell range: (text)", getValues("sellRange"));
        translations.put("0.00 (hint sell range min)", getValues("sellRangeMin"));
        translations.put("0.00 (hint sell range max)", getValues("sellRangeMax"));

        translations.put("Buy range: (text)", getValues("buyRange"));
        translations.put("0.00 (hint buy range min)", getValues("buyRangeMin"));
        translations.put("0.00 (hint buy range max)", getValues("buyRangeMax"));

        translations.put("Difference range: (text)", getValues("differenceRange"));
        translations.put("x (text difference range switch)", getValues("differenceRangeSwitch"));
        translations.put("0.00 (hint difference range min)", getValues("differenceRangeMin"));
        translations.put("0.00 (hint difference range max)", getValues("differenceRangeMax"));

        translations.put("Sell difference range: (text)", getValues("sellDifferenceRange"));
        translations.put("x (text sell difference range switch)", getValues("sellDifferenceRangeSwitch"));
        translations.put("0.00 (hint sell difference range min)", getValues("sellDifferenceRangeMin"));
        translations.put("0.00 (hint sell difference range max)", getValues("sellDifferenceRangeMax"));

        translations.put("Buy difference range: (text)", getValues("buyDifferenceRange"));
        translations.put("x (text buy difference range switch)", getValues("buyDifferenceRangeSwitch"));
        translations.put("0.00 (hint buy difference range min)", getValues("buyDifferenceRangeMin"));
        translations.put("0.00 (hint buy difference range max)", getValues("buyDifferenceRangeMax"));

        translations.put("Sell amount range: (text)", getValues("sellAmountRange"));
        translations.put("0 (hint sell amount range min)", getValues("sellAmountRangeMin"));
        translations.put("0 (hint sell amount range max)", getValues("sellAmountRangeMax"));

        translations.put("Buy amount range: (text)", getValues("buyAmountRange"));
        translations.put("0 (hint buy amount range min)", getValues("buyAmountRangeMin"));
        translations.put("0 (hint buy amount range max)", getValues("buyAmountRangeMax"));

        translations.put("Amount difference range: (text)", getValues("amountDifferenceRange"));
        translations.put("x (text amount difference range switch)", getValues("amountDifferenceRangeSwitch"));
        translations.put("0.00 (hint amount difference range min)", getValues("amountDifferenceRangeMin"));
        translations.put("0.00 (hint amount difference range max)", getValues("amountDifferenceRangeMax"));

        translations.put("Currency: (text)", getValues("currency"));
        translations.put("1 (hint currency value)", getValues("currencyValue"));

        translations.put("Refresh: (text)", getValues("refresh"));
        translations.put("0:0:0:30 (hint refresh value)", getValues("refreshValue"));

        translations.put("Values updated (toast)", getValues("update"));

        translations.put("Are you sure you want to remove this item? (alert)", getValues("remove"));
        translations.put("CANCEL (alert delete)", getValues("removeCancel"));
        translations.put("OK (alert delete)", getValues("removeOk"));

        translations.put("Are you sure you want to restore default translations? (alert)", getValues("restore"));
        translations.put("CANCEL (alert restore)", getValues("restoreCancel"));
        translations.put("OK (alert restore)", getValues("restoreOk"));

        translations.put("CANCEL (alert item)", getValues("itemCancel"));
        translations.put("OK (alert item)", getValues("itemOk"));

        translations.put("Translation (hint)", getValues("translation"));
        translations.put("OK (alert translation)", getValues("translationOk"));

        translations.put("Notifications (notifications)", getValues("notifications"));
    }

    static String[] getValues(String key) {
        switch (key) {
            case "link":
                return new String[]{"Link", "kLink"};
            case "name":
                return new String[]{"Name", "kName"};

            case "notFound":
                return new String[]{"Item not found", "kNotFound"};

            case "sell":
                return new String[]{"Sell:", "kSell"};
            case "buy":
                return new String[]{"Buy:", "kBuy"};

            case "difference":
                return new String[]{"Difference:", "kDifference"};
            case "differenceSwitch":
                return new String[]{"x", "kDifferenceSwitch"};

            case "sellDifference":
                return new String[]{"Sell difference:", "kSellDifference"};
            case "sellDifferenceSwitch":
                return new String[]{"x", "kSellDifferenceSwitch"};

            case "buyDifference":
                return new String[]{"Buy difference:", "kBuyDifference"};
            case "buyDifferenceSwitch":
                return new String[]{"x", "kBuyDifferenceSwitch"};

            case "sellAmount":
                return new String[]{"Sell amount:", "kSellAmount"};
            case "buyAmount":
                return new String[]{"Buy amount:", "kBuyAmount"};

            case "amountDifference":
                return new String[]{"Amount difference:", "kAmountDifference"};
            case "amountDifferenceSwitch":
                return new String[]{"x", "kAmountDifferenceSwitch"};

            case "sellRange":
                return new String[]{"Sell range:", "kSellRange"};
            case "sellRangeMin":
                return new String[]{"0.00", "kSellRangeMin"};
            case "sellRangeMax":
                return new String[]{"0.00", "kSellRangeMax"};

            case "buyRange":
                return new String[]{"Buy range:", "kBuyRange"};
            case "buyRangeMin":
                return new String[]{"0.00", "kBuyRangeMin"};
            case "buyRangeMax":
                return new String[]{"0.00", "kBuyRangeMax"};

            case "differenceRange":
                return new String[]{"Difference range:", "kDifferenceRange"};
            case "differenceRangeSwitch":
                return new String[]{"x", "kDifferenceRangeSwitch"};
            case "differenceRangeMin":
                return new String[]{"0.00", "kDifferenceRangeMin"};
            case "differenceRangeMax":
                return new String[]{"0.00", "kDifferenceRangeMax"};

            case "sellDifferenceRange":
                return new String[]{"Sell difference range:", "kSellDifferenceRange"};
            case "sellDifferenceRangeSwitch":
                return new String[]{"x", "kSellDifferenceRangeSwitch"};
            case "sellDifferenceRangeMin":
                return new String[]{"0.00", "kSellDifferenceRangeMin"};
            case "sellDifferenceRangeMax":
                return new String[]{"0.00", "kSellDifferenceRangeMax"};

            case "buyDifferenceRange":
                return new String[]{"Buy difference range:", "kBuyDifferenceRange"};
            case "buyDifferenceRangeSwitch":
                return new String[]{"x", "kBuyDifferenceRangeSwitch"};
            case "buyDifferenceRangeMin":
                return new String[]{"0.00", "kBuyDifferenceRangeMin"};
            case "buyDifferenceRangeMax":
                return new String[]{"0.00", "kBuyDifferenceRangeMax"};

            case "sellAmountRange":
                return new String[]{"Sell amount range:", "kSellAmountRange"};
            case "sellAmountRangeMin":
                return new String[]{"0", "kSellAmountRangeMin"};
            case "sellAmountRangeMax":
                return new String[]{"0", "kSellAmountRangeMax"};

            case "buyAmountRange":
                return new String[]{"Buy amount range:", "kBuyAmountRange"};
            case "buyAmountRangeMin":
                return new String[]{"0", "kBuyAmountRangeMin"};
            case "buyAmountRangeMax":
                return new String[]{"0", "kBuyAmountRangeMax"};

            case "amountDifferenceRange":
                return new String[]{"Amount difference range:", "kAmountDifferenceRange"};
            case "amountDifferenceRangeSwitch":
                return new String[]{"x", "kAmountDifferenceRangeSwitch"};
            case "amountDifferenceRangeMin":
                return new String[]{"0.00", "kAmountDifferenceRangeMin"};
            case "amountDifferenceRangeMax":
                return new String[]{"0.00", "kAmountDifferenceRangeMax"};

            case "currency":
                return new String[]{"Currency:", "kCurrency"};
            case "currencyValue":
                return new String[]{"1", "kCurrencyValue"};

            case "refresh":
                return new String[]{"Refresh:", "kRefresh"};
            case "refreshValue":
                return new String[]{"0:0:0:30", "kRefreshValue"};

            case "update":
                return new String[]{"Values updated", "kUpdate"};

            case "remove":
                return new String[]{"Are you sure you want to remove this item?", "kRemove"};
            case "removeCancel":
                return new String[]{"CANCEL", "kRemoveCancel"};
            case "removeOk":
                return new String[]{"OK", "kRemoveOk"};

            case "restore":
                return new String[]{"Are you sure you want to restore default translations?", "kRestore"};
            case "restoreCancel":
                return new String[]{"CANCEL", "kRestoreCancel"};
            case "restoreOk":
                return new String[]{"OK", "kRestoreOk"};

            case "itemCancel":
                return new String[]{"CANCEL", "kItemCancel"};
            case "itemOk":
                return new String[]{"OK", "kItemOk"};

            case "translation":
                return new String[]{"Translation", "kTranslation"};
            case "translationOk":
                return new String[]{"OK", "kTranslationOk"};

            case "notifications":
                return new String[]{"Notifications", "kNotifications"};

            default:
                return new String[]{"", ""};
        }
    }
}