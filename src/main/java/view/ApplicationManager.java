package view;

import DTO.ApplicationDTO;
import DTO.AvailabilityDTO;
import DTO.CompetenceDTO;
import DTO.CompetenceProfileDTO;
import controller.Controller;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import view.validators.ValidYear;

/**
 * Class ApplicationManager takes care of everything related to applications
 * in the view.
 */
@Named("applicationManager")
@SessionScoped
public class ApplicationManager implements Serializable 
{
    private static final long serialVersionUID = 16247164405L;
    
    @EJB
    Controller controller;
    
    private List<CompetenceDTO> compList;
    private ArrayList<String> competenceList = new ArrayList<>();
    private ArrayList<String> yearsList = new ArrayList<>();
    private ArrayList<String> competenceAndYearList;
    private ArrayList<String> fromDateList = new ArrayList<>();
    private ArrayList<String> toDateList = new ArrayList<>();
    private ArrayList<String> startDateAndEndDateList;
    private ApplicationDTO specificApplication;
    
    private String competence;
    @ValidYear
    private String years;
    private Date startDate;
    private Date endDate;
    private Boolean showDateMessage;
    private Boolean confirmSuccess;
    private Boolean goToConfirm = false;
    private Boolean clickOnConfirm = false;
    private Boolean confirmFailed = false;
    
    /**
     * Get a competence.
     *
     * @return competence competence
     */
    public String getCompetence(){
        return competence;
    }
    
    /**
     * Set a competence.
     *
     * @param competence competence
     */
    public void setCompetence(String competence){
        this.competence = competence;
    }
    
    /**
     * Get years of a competence.
     *
     * @return years of competence
     */
    public String getYears(){
        return years;
    }
    
    /**
     * Set years of competence.
     *
     * @param years of competence
     */
    public void setYears(String years){
        this.years = years;
    }
    
    private Competence[] comList;
    
    /**
     * Get all types of competences and creates a list of them. This list is a
     * drop down list in the view for the applicants.
     * 
     * @return comList is a list of competences
     */
    public Competence[] getCompetenceValue() 
    {
        comList = null;
        try
        {
            compList = controller.
                getAllCompetences(FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
            comList = new Competence[compList.size()];
            String comName, comId;
            Boolean skip = false;
            ArrayList<Competence> alComp = new ArrayList<>();
            for(CompetenceDTO compList1 : compList) {
                comId = compList1.getCompetence().toString();
                for(String competenceList1 : competenceList) {
                    if(comId.equals(competenceList1)) {
                        skip = true;
                        break;
                    }
                }
                if(!skip) {
                    comName = compList1.getCompetenceName();
                    alComp.add(new Competence(comName, comId));
                }
                skip = false;
            }
            comList = alComp.toArray(new Competence[alComp.size()]);
        }
        catch(Exception e)
        {}
        return comList;
    }
    
    /** 
     * If the list over added competences contains anything then an add button
     * will be active for the user. If the list is empty the add button is 
     * disabled.
     * 
     * @return boolean true if there is any competences in list
     */
    public Boolean getEnableButton(){
        return comList.length > 0;
    }
    
    /**
     * Add a competence to the private competence list.
     *
     * @return JSF version 2.2 bug - Empty string
     */
    public String addCompetence()
    {
        competenceList.add(competence);
        yearsList.add(years);
        competence = null;
        years = null;
        return "";
    }
    
    /**
     * List with the private competence of a person.
     *
     * @return competenceAndYearList list of personal competence
     */
    public ArrayList<String> getCompetenceAndYearList()
    {
        clickOnConfirm = false;
        confirmSuccess = false;
        
        ArrayList<String> al = new ArrayList<>();
        competenceAndYearList = new ArrayList<>();
        
        String c;
        for(int i = 0; i < competenceList.size(); i++) {
            c = competenceList.get(i);
            for(int j = 0; j < compList.size(); j++)
            {
                if(c.equals(compList.get(j).getCompetence().toString()))
                {
                    al.add(compList.get(j).getCompetenceName());
                    break;
                }
            }
        }
        
        for(int i = 0; i < al.size(); i++) {
            competenceAndYearList.add(al.get(i) + " " + yearsList.get(i));
        }
        
        return competenceAndYearList;
    }

    /**
     * List with competences for a specific application.
     *
     * @param id of the specific application
     * @return l list of competences for an application
     */
    public List<CompetenceProfileDTO> getCompetenceAndYearList(Integer id)
    {
        List<CompetenceProfileDTO> l = null;
        try
        {
            l = controller.getCompetenceProfileByApplicationId(id);
        }
        catch(Exception e)
        {}
        return l;
    }
    
    /**
     * Event listener: user gets available dates when user selects date section.
     *
     * @param event listener
     */
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        facesContext.addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
    
    /**
     * Get availability of start dates.
     *
     * @return startDate start date
     */
    public Date getStartDate() {
        return startDate;
    }
 
    /**
     * Set availability of start dates.
     *
     * @param startDate start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    /**
     * Get availability of end dates.
     *
     * @return endDate end date
     */
    public Date getEndDate() {
        return endDate;
    }
 
    /**
     * Set availability of end dates.
     *
     * @param endDate end date 
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    /**
     * Add availability of dates to availability list.
     * 
     * @return JSF version 2.2 bug - Empty string
     */
    public String addDates()
    {
        if(startDate.after(endDate))
        {
            showDateMessage = true;
            return "";
        }
        
        showDateMessage = false;    
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        fromDateList.add(df.format(startDate));
        toDateList.add(df.format(endDate));
        startDate = null;
        endDate = null;
        return "";
    }
    
    /**
     * Error message about start and end dates.
     *
     * @return boolean true if an error occoured with start and end dates
     */
    public Boolean getShowDateMessage(){
        return showDateMessage;
    }
    
    /**
     * Creates a list of start and end dates. User gets a list in the view.
     *
     * @return startDateAndEndDateList list of start and end dates
     */
    public ArrayList<String> getStartDateAndEndDateList()
    {
        goToConfirm = !(fromDateList.isEmpty() || fromDateList == null);
        
        ArrayList<String> al = new ArrayList<>();
        startDateAndEndDateList = new ArrayList<>(); 
        
        for(int i = 0; i < fromDateList.size(); i++)
        {
            startDateAndEndDateList.add(fromDateList.get(i) + " --- " + toDateList.get(i));
        }
        
        return startDateAndEndDateList;
    }
    
    /**
     * Get availability periods for a specific application.
     *
     * @param id of the specific application
     * @return l list with availability periods
     */
    public List<AvailabilityDTO> getStartDateAndEndDateList(Integer id)
    {
        List<AvailabilityDTO> l = null;
        try
        {
            l = controller.getAvailabilityByApplicationId(id);
        }
        catch(Exception e)
        {}
        return l;
    }
    
    /**
     * User removes a competens that previously was set.
     *
     * @param currentComp chosen competence to remove
     * @return JSF version 2.2 bug - Empty string
     */
    public String removeCurrentComp(String currentComp)
    {
        String[] arr;
        String c = "";
        String value = "";
        
        arr = currentComp.split(" ");
        int j = 0;
        String y = "";

        while(true)
        {
            try 
            {
                Double.parseDouble(arr[j]);
                y = arr[j];
                break;
            } 
            catch (NumberFormatException nfe) 
            {
                c += arr[j] + " ";
            }
            j++;
        }
        
        //Get competence id
        for (CompetenceDTO compList1 : compList) {
            if ((compList1.getCompetenceName() + " ").equals(c)) 
            {
                value = compList1.getCompetence().toString();
                break;
            }
        }
        
        //Get position in competence list 
        int pos = 0;
        for(int i = 0; i < competenceList.size(); i++)
        {
            if(competenceList.get(i).equals(value))
            {
                pos = i;
                break;
            }
        }
        competenceList.remove(value);
        yearsList.remove(pos);
        
        return "";
    }
    
    /**
     * Remove a specific period.
     *
     * @param currentPeriod current period
     * @return JSF version 2.2 bug - Empty string
     */
    public String removeCurrentPeriod(String currentPeriod)
    {
        String[] arr = currentPeriod.split(" ");
        for(int i = 0; i < fromDateList.size(); i++)
        {
            if(fromDateList.get(i).equals(arr[0]) && toDateList.get(i).equals(arr[2]))
            {
                fromDateList.remove(i);
                toDateList.remove(i);
                break;
            }
        }
        
        goToConfirm = !(fromDateList.isEmpty() || fromDateList == null);
        return "";
    }
    
    /**
     * Remove all competences and periods.
     *
     * @return JSF version 2.2 bug - Empty string
     */
    public String clearAll()
    {
        competenceList = new ArrayList<>();
        yearsList = new ArrayList<>();
        fromDateList = new ArrayList<>();
        toDateList = new ArrayList<>();
        confirmSuccess = false;
        goToConfirm = false;
        clickOnConfirm = false;
        return "";
    }
    
    /**
     * Checks that the applicant has at least once set a availability period.
     *
     * @return JSF version 2.2 bug - Empty string
     */
    public String checkValues()
    {
        goToConfirm = !(fromDateList.isEmpty() || fromDateList == null);
        clickOnConfirm = true;
        return "";
    }
    
    /**
     * Checks if applicant can go on and confirm.
     *
     * @return boolean true yes else no
     */
    public Boolean getGoToConfirm()
    {
        return goToConfirm;
    }
    
    /**
     * Checks if the applicant has pushed the confirm button.
     *
     * @return boolean true if yes else no
     */
    public Boolean getClickOnConfirm()
    {
        return clickOnConfirm;
    }
    
    /**
     * Used to pass the application and send it to the database.
     *
     * @return JSF version 2.2 bug - Empty string
     */
    public String confirmApplication()
    {
        String username = 
               FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
        Integer jobId = Integer.parseInt(
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("jobId"));
        try
        {
            controller.createApplication
                (competenceList, yearsList, fromDateList, toDateList, username, jobId);
            confirmSuccess = true;
            competenceList = new ArrayList<>();
            yearsList = new ArrayList<>();
            fromDateList = new ArrayList<>();
            toDateList = new ArrayList<>();
            goToConfirm = false;
            clickOnConfirm = false;
        }
        catch(Exception e)
        {
            confirmFailed = true;
        }        
        return "";
    }
    
    /**
     * Shows success or not message.
     *
     * @return boolean
     */
    public Boolean getConfirmSuccess(){
        return confirmSuccess;
    }
    
    /**
     * Get list of finished applications for a specific user.
     *
     * @param username user
     * @return l list with applications that have been finished
     */
    public List<ApplicationDTO> getApplicationList(String username)
    {
        List<ApplicationDTO> l = null;
        try
        {
            l = controller.getApplicationsByUsername(username);
        }
        catch(Exception e)
        {}
        return l;
    }
    
    /**
     * Get a jobs name by id.
     *
     * @param id job id
     * @return s specific job name
     */
    public String getJobNameById(Integer id)
    {
        String s = null;
        try
        {
            s = controller.getJobNameById(id, 
                    FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
        }
        catch(Exception e)
        {}
        return s;
    }
    
    /**
     * Get competence name by id.
     *
     * @param id of a specific competence
     * @return s specific competence name
     */
    public String getCompetenceNameById(Integer id)
    {
        String s = null;
        try
        {
            s = controller.getCompetenceNameById(id, 
                    FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
        }
        catch(Exception e)
        {}
        return s;
    }
    
    /**
     * Get name of a status by id.
     *
     * @param id of a specific id
     * @return s status of id
     */
    public String getStatusNameById(Integer id)
    {
        String s = null;
        try
        {
            s = controller.getStatusNameById(id, 
                    FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
        }
        catch(Exception e)
        {}
        return s;
    }
    
    /**
     * Set a specific application.
     *
     * @param specificApplication specific application
     */
    public void setSpecificApplication(ApplicationDTO specificApplication){
        this.specificApplication = specificApplication;
    }
    
    /**
     * Get the specific application.
     *
     * @return specificApplication specific application
     */
    public ApplicationDTO getSpecificApplication(){
        return specificApplication;
    }
    
    /**
     * Message user if the application was a success or not.
     *
     * @return boolean true if success
     */
    public Boolean getConfirmFailed()
    {
        return confirmFailed;
    }
    
    /**
     * Set a confirmation failure of application.
     *
     * @param confirmFailed true if fail
     */
    public void setConfirmFailed(Boolean confirmFailed)
    {
        this.confirmFailed = confirmFailed;
    }
}