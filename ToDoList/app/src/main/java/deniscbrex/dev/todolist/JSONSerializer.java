package deniscbrex.dev.todolist;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JSONSerializer {

    private String mFileName; // Nombre del fichero JSON que va a guardar la clase
    private Context mContext; // Contexto de donde se va a guardar el fichero JSON

    //Constructor del objeto que va a serializar en ficheros JSON
    public JSONSerializer(String filename, Context context){
        this.mFileName = filename;
        this.mContext = context;
    }

    public void save(List<Note> notes) throws IOException, JSONException{

        JSONArray jArray = new JSONArray();

        //Convierte cada una de las Note en objetos JSON y lso guarda en JSON Array
        for(Note n: notes){
            jArray.put(n.convertNoteToJSON());
        }

        //el writer guarda el fichero jSON
        Writer writer = null;

        try {

            //abre el fichero donde se guarda el JSON
            OutputStream out = mContext.openFileOutput(mFileName, mContext.MODE_PRIVATE);

            //el writer sabe donde guardar el contenido
            writer = new OutputStreamWriter(out);

            writer.write(jArray.toString());
        }finally {
            if(writer != null){
                writer.close();
            }
        }
    }

    public ArrayList<Note> load() throws IOException, JSONException{
        //Array de objetos notes en Java
        ArrayList<Note> notes = new ArrayList<Note>();

        //Buffered reader para leer fichero JSON
        BufferedReader reader = null;
        try {
            //Abre el fichero JSON
            InputStream in = mContext.openFileInput(mFileName);

            //el reader ya sabe de donde leer los datos
            reader = new BufferedReader(new InputStreamReader(in));

            //lee los ficheros JSON con un string builder
            StringBuilder jsonString = new StringBuilder();

            //variable para leer linea actual
            String currentLine = null;

            //Lee el fichero JSON entero y lo pasa a String
            while ((currentLine = reader.readLine())!=null){
                jsonString.append(currentLine);
            }

            //Pasar de un array entero de strings a un array de objetos JSON
            JSONArray jArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for(int i = 0; i < jArray.length(); i++){
                notes.add(new Note(jArray.getJSONObject(i)));
            }


        }catch (FileNotFoundException e){

        }
        finally {
            if(reader != null){
                reader.close();
            }
        }

        return notes;
    }
}
