package deniscbrex.dev.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class DialogNewNote extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);


        final EditText editTitle =  (EditText) dialogView.findViewById(R.id.editTitle);
        final EditText editDescription = (EditText) dialogView.findViewById(R.id.editDescription);

        final CheckBox checkboxIdea = (CheckBox) dialogView.findViewById(R.id.checkboxIdea);
        final CheckBox checkboxTodo = (CheckBox) dialogView.findViewById(R.id.checkBoxTodo);
        final CheckBox checkboxImportant = (CheckBox) dialogView.findViewById(R.id.checkBoxImportant);

        Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        Button btnOk = (Button) dialogView.findViewById(R.id.btnOk);

        builder.setView(dialogView)
                .setMessage(R.string.add_note);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //crea nota vacia
                Note newNote = new Note();

                //configurando las 5 variables de la nota creada
                newNote.setTitle(editTitle.getText().toString());
                newNote.setDescription(editDescription.getText().toString());

                newNote.setIdea(checkboxIdea.isChecked());
                newNote.setTodo(checkboxTodo.isChecked());
                newNote.setImportant(checkboxImportant.isChecked());


                //Casting a MainActivity que es quien llamo al dialogo
                MainActivity callingActivity = (MainActivity) getActivity();
                //Notificar a la MainActivity que cree una nueva nota
                callingActivity.CreateNewNote(newNote);

                //cierra el dialogo
                dismiss();
            }
        });

        return builder.create();
    }
}
