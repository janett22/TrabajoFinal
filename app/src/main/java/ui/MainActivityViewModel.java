package ui;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import reques.ApiClient;
//Subiendo vm
public class MainActivityViewModel extends ViewModel {
    MutableLiveData<String> resultadoMutable;
    MutableLiveData<Boolean> okMutable;
    public LiveData<String> getResultadoMutable(){
        if(resultadoMutable==null){
            resultadoMutable= new MutableLiveData<>();
        }
        return resultadoMutable;
    }
    public LiveData<Boolean> getOkMutable(){
        if(okMutable==null){
            okMutable= new MutableLiveData<>();
        }
        return okMutable;
    }
    public void verificarDatos(String usuario, String contrasenia){
        if(usuario !=null && contrasenia!=null && usuario.length()>0 && contrasenia.length()>0){
            ApiClient api= ApiClient.getApi();
            if (api.login(usuario, contrasenia)!=null){
                resultadoMutable.setValue("Bienvenidos a Inmobiliaria AGV");
                okMutable.setValue(true);
            }else{
                resultadoMutable.setValue("Datos incorrectos, por favor intente nuevamente");
            }
        }else{
            resultadoMutable.setValue("Por favor complete todos los campos");
        }

    }

}