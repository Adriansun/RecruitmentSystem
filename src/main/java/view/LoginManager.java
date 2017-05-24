package view;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class takes care of login and logout of user, saves flags for the XHTML 
 * pages.
 */
@ManagedBean(name="loginManager")
@SessionScoped
public class LoginManager implements Serializable {
    private static final long serialVersionUID=16247164405L;

    private String username;
    private String password;
    private Boolean loginAsApplicantSuccess=false;
    private Boolean loginAsRecruiterSuccess=false;
    private Boolean logoutSuccess=true;
    private Boolean showMessage=false;

    /**
     * Get HTTP servlet query to log in the user through the JDBC realm on the
     * Glassfish server. Sets flags at valid points based on user profile.
     */
    public void login() {
        HttpServletRequest request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try
        {
            request.login(username, password);
            logoutSuccess=false;
            if(request.isUserInRole("applicant")) { loginAsApplicantSuccess=true; }
            else if(request.isUserInRole("recruiter")) { loginAsRecruiterSuccess=true; }
        }
        catch(ServletException se) {
            showMessage=true;
            username=null;
        }
    }

    /**
     * Get HTTP session to invalidate login. Sets flags according to the user
     * profile.
     */
    public void logout() {
        HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if(loginAsApplicantSuccess) { loginAsApplicantSuccess=false; }
        else if(loginAsRecruiterSuccess) { loginAsRecruiterSuccess=false; }
        if(session!=null) { session.invalidate(); }
        logoutSuccess = true;
    }

    /**
     * Get user name.
     *
     * @return username username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username for login.
     *
     * @param username username
     */
    public void setUsername(String username) {
        this.username=username;
    }

    /**
     * Get user password.
     *
     * @return password password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set user password for login.
     *
     * @param password password
     */
    public void setPassword(String password) {
        this.password=password;
    }


    /**
     * Get flag for showing error during login for the user.
     *
     * @return showMessage shows error message
     */
    public Boolean getShowMessage() {
        return showMessage;
    }

    /**
     * Get flag for login success for the user.
     *
     * @return loginAsApplicantSuccess boolean
     */
    public boolean getLoginAsApplicantSuccess() {
        return loginAsApplicantSuccess;
    }

    /**
     * Get flag for success of login for recruiter.
     *
     * @return loginAsRecruiterSuccess boolean
     */
    public boolean getLoginAsRecruiterSuccess() {
        return loginAsRecruiterSuccess;
    }

    /**
     * Get flag for success logout of user.
     *
     * @return logoutSuccess boolean
     */
    public boolean getLogoutSuccess() {
        return logoutSuccess;
    }
}
