package springPuzzlers.quoters;

/**
 * Created by Andriy_Yarish on 12/23/2016.
 */
public class ProfilingControler implements ProfilingControlerMBean{
    private boolean enabled ;

    public ProfilingControler() {

    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
