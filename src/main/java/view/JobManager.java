package view;

import DTO.JobDTO;
import controller.Controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * The class works with everything to do with the job view for the user.
 */
@Named("jobManager")
@SessionScoped
public class JobManager implements Serializable 
{
    private static final long serialVersionUID = 16247164405L;
    
    @EJB
    Controller controller;
    
    private List<JobDTO> jobs;
    private String currentJob;
    private Integer currentJobId;
    
    /**
     * Get list with different job types.
     *
     * @return list job list
     */
    public List getJobs(){
        List<String> list = new ArrayList();
        try
        {
            jobs = controller.getJobs(FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
        
            for (JobDTO job : jobs) {
                list.add(job.getJobTypeName());
            }
        }
        catch(Exception e)
        {}
        return list;
    }
    
    /**
     * Get current job of user.
     *
     * @return currentJob current job
     */
    public String getCurrentJob()
    {
        try
        {
            currentJob = controller.getJobNameById(currentJobId, 
                FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage());
        }
        catch(Exception e)
        {}
        return currentJob;
    }
    
    /**
     * Set current job.
     *
     * @param currentJob current job
     */
    public void setCurrentJobId(String currentJob)
    {
        for (JobDTO job : jobs) 
            if (currentJob.equals(job.getJobTypeName())) 
                currentJobId = job.getJob();
    }
    
    /**
     * Get id of current job of user.
     *
     * @return id of the  current job
     */
    public String getCurrentJobId(){
        return currentJobId.toString();
    }
}
