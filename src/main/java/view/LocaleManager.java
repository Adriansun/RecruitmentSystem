package view;

import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * The class sets the user prefered language for the whole application/program.
 */
@Named(value="locale")
@SessionScoped
public class LocaleManager implements Serializable
{
    private String lang;
    
    /**
     * Sets the default values or value from the user.
     */
    @PostConstruct
    public void init(){
        lang = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale().getLanguage();
    }
    
    /**
     * Get the current set language.
     *
     * @return lang language
     */
    public String getLang() {
        return lang;
    }

    /**
     * Set the language.
     *
     * @param lang language
     */
    public void setLang(String lang) {
        this.lang = lang;
    }
    
    /**
     * Change to another language.
     */
    public void changeLocale() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(lang));
    }
}
