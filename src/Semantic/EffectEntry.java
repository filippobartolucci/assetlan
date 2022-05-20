package Semantic;

public class EffectEntry {
    private boolean status;

    public EffectEntry() {
        this.status = false;
    }

    public EffectEntry(EffectEntry e) {
        this.status = e.getStatus();
    }

    public void setTrue(){
        this.status = true;
    }

    public void setFalse(){
        this.status = false;
    }

    public boolean getStatus() {
        return this.status;
    }
}
