package Semantic;

public class EffectEntry {
    // Status field is used for effects valuation
    // For a var:
    // 		true: var is initialized
    // 		false: var is not initialized
    // For an asset:
    // 		true: non empty asset
    // 		false: empty asset
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

    public void max(EffectEntry e){
        this.status = e.status || this.status;
    }

    public String toString(){
        return String.valueOf(this.status);
    }
}
