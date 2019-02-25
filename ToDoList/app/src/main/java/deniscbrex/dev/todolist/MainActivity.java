package deniscbrex.dev.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * @author Denis Cuenca Brex
 */

public class MainActivity extends AppCompatActivity {

    private Note nTempNote = new Note();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Crea una instancia de Dialog Show Note
                DialogShowNote dialog = new DialogShowNote();
                //Indica al dialogo que nota tiene que mostrar
                dialog.sendNotSelected(nTempNote);
                //Muestra el dialogo por pantalla
                dialog.show(getSupportFragmentManager(), "note_create");
            }
        });
    }

    public void CreateNewNote(Note newNote){
        nTempNote = newNote;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add){
            //Instanciar dialogo para crear nota
            DialogNewNote dialog = new DialogNewNote();
            //Mostrar el dialogo en pantalla
            dialog.show(getSupportFragmentManager(), "note_show" );
        }



        return false;
    }
}
