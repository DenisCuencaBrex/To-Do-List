package deniscbrex.dev.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Cuenca Brex
 */

public class MainActivity extends AppCompatActivity {

    private NoteAdapter mNoteAdapter;

    private boolean mSound;
    private int mAnimationOption;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoteAdapter = new NoteAdapter();

        ListView listNote = (ListView) findViewById(R.id.list_view);

        listNote.setAdapter(mNoteAdapter);

        listNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //carga la nota que es clickeada
                Note tempNote = mNoteAdapter.getItem(position);
                //Crea la instancia de Show Note
                DialogShowNote dialog = new DialogShowNote();
                dialog.sendNotSelected(tempNote);
                dialog.show(getSupportFragmentManager(), "asf");

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        mNoteAdapter.saveNotes();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mPrefs = getSharedPreferences("To do list", MODE_PRIVATE);
        mSound = mPrefs.getBoolean("sound", true);
        mAnimationOption = mPrefs.getInt("anim option", SettingActivity.FAST);
    }

    public void CreateNewNote(Note newNote){
        mNoteAdapter.AddNote(newNote);
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

        if(item.getItemId() == R.id.action_settings){
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }

        return false;
    }

    public class NoteAdapter extends BaseAdapter {

        List<Note> noteList = new ArrayList<Note>();
        private JSONSerializer mSerializer;

        public NoteAdapter(){
            mSerializer = new JSONSerializer("ToDoList.json", MainActivity.this.getApplicationContext());

            try{
                noteList = mSerializer.load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public void saveNotes(){
            try{
                mSerializer.save(noteList);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public int getCount() {
            return noteList.size();
        }

        @Override
        public Note getItem(int position) {
            return noteList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //infla la lista
            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item, parent, false);
            }


            //Carga todos los widgets
            TextView textViewTitle = (TextView) convertView.findViewById(R.id.text_view_title);
            TextView textViewDescription = (TextView) convertView.findViewById(R.id.text_view_description);

            ImageView imageViewImportant = (ImageView) convertView.findViewById(R.id.image_view_important);
            ImageView imageViewTodo = (ImageView) convertView.findViewById(R.id.image_view_todo);
            ImageView imageViewIdea = (ImageView) convertView.findViewById(R.id.image_view_idea);


            //cambia el titulo y la descripcion en el layout
            Note currentNote = noteList.get(position);

            if(!currentNote.isImportant()){
                imageViewImportant.setVisibility(View.GONE);
            }
            if(!currentNote.isTodo()){
                imageViewTodo.setVisibility(View.GONE);
            }
            if(!currentNote.isIdea()){
                imageViewIdea.setVisibility(View.GONE);
            }


            textViewTitle.setText(currentNote.getTitle());
           textViewDescription.setText(currentNote.getDescription());

            return convertView;
        }

        public void AddNote(Note n){
            noteList.add(n);
            notifyDataSetChanged();
        }

    }
}
