package view;

import view.validators.ValidSSN;
import controller.Controller;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import view.validators.ValidEmail;

/**
 * Class handles all registrations/input by the user in the view.
 */
@Named("registerManager")
@ConversationScoped
public class RegisterManager implements Serializable 
{
    private static final long serialVersionUID = 16247164405L;
    
    @EJB
    Controller controller;
    
    private String name;
    private String surname;
    @ValidSSN
    private String ssn;
    @ValidEmail
    private String email;
    private String username;
    private String password;
    private String repeatPassword;
    private Boolean registerSuccess = false;
    private Boolean showPasswordMessage;
    private Boolean showMessage;
    private Boolean registrationFailed = false;
    
    @Inject
    private Conversation conversation; 
    
    /**
     * Conversation scoped bean start.
     * All values are stored. 
     */
    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    /**
     * Conversation scoped bean stop.
     * All values are removed.
     */
    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    /**
     * Conversation scoped bean end.
     * Call stopConversation and abort registration.
     */
    public void endConversation()
    {
        stopConversation();
        registrationFailed = false;
    }
    
    /**
     * Sets user first name.
     * @param name first name
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Get user first name.
     *
     * @return name first name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Set user surname.
     *
     * @param surname surname
     */
    public void setSurname(String surname){
        this.surname = surname;
    }
    
    /**
     * Get user surname.
     *
     * @return surname surname
     */
    public String getSurname(){
        return surname;
    }
    
    /**
     * Set user SSN.
     *
     * @param ssn SSN
     */
    public void setSsn(String ssn){
        this.ssn = ssn;
    }
    
    /**
     * Get user SSN.
     *
     * @return ssn SSN
     */
    public String getSsn(){
        return ssn;
    }
    
    /**
     * Set user e-mail.
     *
     * @param email e-mail
     */
    public void setEmail(String email){
        this.email = email;
    }
    
    /**
     * Get user e-mail.
     *
     * @return email e-mail
     */
    public String getEmail(){
        return email;
    }
    
    /**
     * Set user username.
     *
     * @param username username
     */
    public void setUsername(String username){
        this.username = username;
    }
    
    /**
     * Get user username.
     *
     * @return username username
     */
    public String getUsername(){
        return username;
    }
    
    /**
     * Set user password.
     *
     * @param password password
     */
    public void setPassword(String password){
        this.password = password;
    }
    
    /**
     * Get user password.
     *
     * @return password password
     */
    public String getPassword(){
        return password;
    }
    
    /**
     * Set user repeat of password.
     *
     * @param repeatPassword verify password
     */
    public void setRepeatPassword(String repeatPassword){
        this.repeatPassword = repeatPassword;
    }
    
    /**
     * Get user repeated password
     *
     * @return repeatPassword repeated password 
     */
    public String getRepeatPassword(){
        return repeatPassword;
    }
    
    /**
     * Set a type of message boolean.
     *
     * @param show show message
     */
    public void setShowMessage(Boolean show){
        this.showMessage = show;
    }
    
    /**
     * Message was shown boolean.
     *
     * @return showMessage showed message
     */
    public Boolean getShowMessage(){
        return showMessage;
    }
    
    /**
     * Boolean about if the registration was a success or not.
     *
     * @return registerSuccess boolean
     */
    public Boolean getRegisterSuccess(){
        return registerSuccess;
    }
    
    /**
     * Show password message boolean.
     *
     * @param show boolean
     */
    public void setShowPasswordMessage(Boolean show){
        this.showPasswordMessage = show;
    }
    
    /**
     * Boolean, if the show password success message should be shown or not.
     *
     * @return showPasswordMessage boolean
     */
    public Boolean getShowPasswordMessage(){
        return showPasswordMessage;
    } 
    
    /**
     * Checks if password and verified password is the same. If the do not match
     * then show message.
     * 
     * @return JSF version 2.2 bug - Empty string
     */
    public String register()
    {
        if(!(password.equals(repeatPassword)))
        {
            showPasswordMessage = true; 
            return "";
        }
        
        try
        {
            if(!controller.register(name, surname, ssn, email, username, password))
            {
                registerSuccess = false;
                showMessage = true;
                return "";
            }
            
            registerSuccess = true;
            registrationFailed = false;
        }
        catch(Exception e)
        {
            startConversation();
            name = null;
            surname = null;
            username = null;
            email = null;
            ssn = null;
            registrationFailed = true;
            registerSuccess = false;
        }
        
        return "";
    }
    
    /**
     * Message if registration was a success or not.
     *
     * @return registrationFailed boolean
     */
    public Boolean getRegistrationFailed()
    {
        return registrationFailed;
    }
    
    /**
     * Sets if registration failed.
     *
     * @param regFailed true if registration failed
     */
    public void setRegistrationFailed(Boolean regFailed)
    {
        this.registrationFailed = regFailed;
    }
}
