package edu.upc.login.Fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;

import edu.upc.login.R;

public class FragmentCamara extends Fragment {

    ImageView imagen;
    private final String CARPETA_RAIZ = "misImagenesPrueba/";
    private final String RUTA_IMAGEN = CARPETA_RAIZ + "misFotos";
    //File ruta = new File ("misImagenesPrueba/misfotos");
    String path = "";

    String pathSDCard = System.getenv("SECONDARY_STORAGE");
    String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};


    final int COD_SELECCIONA = 10;
    final int COD_FOTO = 20;
    private static final int RESULT_OK = 0;
    final int cameraRequest = 1880;
    Button cameraBtn;
    private View RootView;
    String nombreImagen = "";

    public boolean canOpenCamera = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View RootView = inflater.inflate(R.layout.camara_fragment, container, false);
        this.RootView = RootView;
        imagen = RootView.findViewById(R.id.Photo);
        cameraBtn = RootView.findViewById(R.id.openCamaraid);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAllOK();
                cargarImagen();
            }
        });
        return RootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void CheckAllOK() {
        //Comprovar si hay camara
        if (checkCameraHardware(this.getContext())){
            Log.i("INFO", "He llegado?");

            //comprovar los permisos mientras corre la aplicación

            if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != getActivity().getPackageManager().PERMISSION_GRANTED && getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != getActivity().getPackageManager().PERMISSION_GRANTED){
                getActivity().requestPermissions(permissions,cameraRequest);
            } else
                //tiene permiso
                canOpenCamera = true;
                Log.i("INFO", "Hay permiso: "+ canOpenCamera);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(hasPermission(permissions[0]) && hasPermission(permissions[1])) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.length != 0 && grantResults[0] == getActivity().getPackageManager().PERMISSION_GRANTED)) {
                // permission was granted
                canOpenCamera = true;
                Log.i("RequestPermission", "Hay permiso: "+ canOpenCamera);
            }
        }
    }

    public void EstadoSd (){

        String estado = Environment.getExternalStorageState();
        Log.i("SD","Estado: "+estado);

    }


    public static boolean isSDCardAvailable(Context context) {
        File[] storages = ContextCompat.getExternalFilesDirs(context, null);
        if (storages.length > 1 && storages[0] != null && storages[1] != null)
            return true;
        else
            return false;
    }

    private boolean checkCameraHardware (Context context) {
        boolean aux;
        aux = context.getPackageManager().hasSystemFeature(getActivity().getPackageManager().FEATURE_CAMERA_ANY);
        Log.i("INFO", "Hay Camara: "+aux);
        return aux;
    }

    private boolean hasPermission (String per) {
        boolean aux2;
        aux2 = getActivity().getPackageManager().PERMISSION_GRANTED == getActivity().checkSelfPermission(per);
        Log.i("INFO", "permiso: "+aux2);
        return aux2;
    }

    private void cargarImagen() {
        final CharSequence[] opciones = {"Hacer Foto", "Cargar Foto", "Cancelar"};
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(getContext());
        alertOpciones.setTitle("Seleccione una Opción");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (opciones[i].equals("Hacer Foto")) {
                    if (canOpenCamera)
                        hacerFoto();
                    else
                        Log.i("Error", "No se ha podido abrir la camera");
                } else {
                    if (opciones[i].equals("Cargar Foto")) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent, COD_SELECCIONA);
                    } else {
                        EstadoSd();
                        dialog.dismiss();

                    }
                }
            }
        });
        alertOpciones.show();
    }

    private void hacerFoto() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            photoFile = createImageFile();
            // Continue only if the File was successfully created
            if (photoFile != null) {
                //Uri photoURI = Uri.fromFile(photoFile);
                Uri photoURI = FileProvider.getUriForFile(
                        getActivity(),
                        "edu.upc.login.provider",
                        photoFile);
                Log.i("Meta", "Media:"+ MediaStore.EXTRA_OUTPUT+" "+photoURI);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, COD_FOTO);

                Bitmap bitmap = BitmapFactory.decodeFile(path);
                imagen.setImageBitmap(bitmap);
                Log.i("META","bitmap: "+bitmap);
            }
        }
    }

    private File createImageFile() {
        // Create an image file name
        String imageFileName = "Avatar";
        File storageDir = getActivity().getExternalFilesDir(pathSDCard);
        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
            // Save a file: path for use with ACTION_VIEW intents
            path = image.getAbsolutePath();
        }catch (Exception e){
            e.printStackTrace();
        }
        return image;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("Meta", "He llegao? "+requestCode+" "+COD_FOTO);

        switch (requestCode) {

            case COD_SELECCIONA:
                    Uri miPath = data.getData();
                    Log.i("Meta", "He llegao?");
                    imagen.setImageURI(miPath);
                    break;

            case COD_FOTO:
                    /*Log.i("Meta", "Intent: "+data);
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    Log.i("Meta", "He llegao: "+ imageBitmap);
                    String dest = this.getActivity().getExternalFilesDir(null).toString();
                    Log.i("Meta", "dest: "+dest);
                    imagen.setImageBitmap(imageBitmap);*/
                    break;
            }

    }
}