package deniscbrex.dev.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DialogShowNote extends DialogFragment {

    private Note mNote;

    public void sendNotSelected(Note noteSelected){

        this.mNote = noteSelected;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_show_note, null);

        TextView txtTitle = (TextView) dialogView.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) dialogView.findViewById(R.id.txtDescription);

        txtTitle.setText(mNote.getTitle());
        txtDescription.setText(mNote.getDescription());

        ImageView ivImportant = (ImageView) dialogView.findViewById(R.id.ImageViewImportant);
        ImageView ivTodo = (ImageView) dialogView.findViewById(R.id.ImageViewTodo);
        ImageView ivIdea = (ImageView) dialogView.findViewById(R.id.ImageViewIdea);

        if(!mNote.isImportant()){
            ivImportant.setVisibility(View.GONE);
        }
        if(!mNote.isTodo()){
            ivTodo.setVisibility(View.GONE);
        }
        if(!mNote.isIdea()){
            ivIdea.setVisibility(View.GONE);
        }


        Button btnOk = (Button) dialogView.findViewById(R.id.btnOk);

        builder.setView(dialogView)
                .setMessage(R.string.show_note);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return builder.create();
    }
}
