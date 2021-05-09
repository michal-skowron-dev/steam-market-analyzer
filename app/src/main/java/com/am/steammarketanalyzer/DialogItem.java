package com.am.steammarketanalyzer;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.koushikdutta.ion.Ion;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DialogItem extends AppCompatDialogFragment {

    Context context;

    private EditText linkView, nameView;
    private ItemListener listener;

    private int id;
    private String imageLink, link, content;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_item, null);

        linkView = view.findViewById(R.id.link);
        nameView = view.findViewById(R.id.name);

        context = Objects.requireNonNull(this.getActivity());
        loadSettings();

        linkView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) { getWebContent(); }
        });

        builder.setView(view)
                .setNegativeButton(ActivityMain.getSharedPreferences(context).getString(DialogTranslation.getValues("itemCancel")[1], DialogTranslation.getValues("itemCancel")[0]), (dialogInterface, i) -> { })
                .setPositiveButton(ActivityMain.getSharedPreferences(context).getString(DialogTranslation.getValues("itemOk")[1], DialogTranslation.getValues("itemOk")[0]), (dialogInterface, i) -> listener.getData(id, imageLink, nameView.getText().toString(), link));
        return builder.create();
    }

    private void getWebContent() {
        link = getShortenedLink(linkView.getText().toString());
        String fullLink = String.format("%s%s", getString(R.string.site), link);

        Ion.with(this).load(fullLink).asString().setCallback((e, result) -> extractData(result));
    }

    private void extractData(String result) {
        content = result;
        if (content == null) { id = 0; return; }

        String value = findValue("ItemActivityTicker.Start\\( (.*) ", 1);
        id = value.equals("") ? 0 : Integer.parseInt(value);

        value = findValue("(?<=, https://)(.*)2x 2x", 0);
        imageLink = value.equals("") ? "" : value.replace(getString(R.string.image_prefix_old), getString(R.string.image_prefix_new));

        value = findValue("market_name\":\"(.*?)\"", 1);
        String name = value.equals("") ? "" : value;

        nameView.setText(name);
    }

    private String findValue(String regex, int group) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) return matcher.group(group);
        return "";
    }

    private String getShortenedLink(String link) {
        String[] parts = link.split("/");

        if (parts.length < 2) return "";
        return String.format("%s/%s", parts[parts.length - 2], parts[parts.length - 1]);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try { listener = (ItemListener) context; }
        catch (ClassCastException e) { throw new ClassCastException(); }
    }

    public interface ItemListener { void getData(int id, String imageLink, String name, String link); }

    private void loadSettings() {
        linkView.setHint(ActivityMain.getSharedPreferences(context).getString(DialogTranslation.getValues("link")[1], DialogTranslation.getValues("link")[0]));
        nameView.setHint(ActivityMain.getSharedPreferences(context).getString(DialogTranslation.getValues("name")[1], DialogTranslation.getValues("name")[0]));
    }
}