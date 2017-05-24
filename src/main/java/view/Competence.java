package view;

/**
 * Competence class handles the drop down list in the application view for the 
 * different competences. 
 */
public class Competence
{
    private String comLabel;
    private String comValue;
    
    /**
     * Constructor for the competence drop list.
     *
     * @param comLabel visible values for the user
     * @param comValue behind the scene values
     */
    public Competence(String comLabel, String comValue)
    {
        this.comLabel = comLabel;
        this.comValue = comValue;
    }

    /**
     * Get the list for the user.
     * 
     * @return comLabel the list
     */
    public String getComLabel(){
        return comLabel;
    }
    
    /**
     * Get the list of background values.
     * 
     * @return comValues behind the scene values list
     */
    public String getComValue(){
        return comValue;
    }
}