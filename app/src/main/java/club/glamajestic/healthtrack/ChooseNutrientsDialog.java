package club.glamajestic.healthtrack;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.CheckBox;

import java.util.ArrayList;

import persistence.DataFoods;
import persistence.DatabaseDefinition;

public class ChooseNutrientsDialog extends DialogFragment {


    private ArrayList selectedItems;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        selectedItems = new ArrayList();
        String[] nutrients = DataFoods.allNutrientNames;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose the Nutrients to Display")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(nutrients, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {

                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    selectedItems.add(which);
                                } else if (selectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    selectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }

}
