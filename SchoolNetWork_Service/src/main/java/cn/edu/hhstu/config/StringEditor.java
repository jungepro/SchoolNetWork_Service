package cn.edu.hhstu.config;

import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

import java.beans.PropertyEditorSupport;

/**
 * web 字符串编辑器，将字符进行HTML转义（防止XSS攻击）
 * @see org.springframework.beans.propertyeditors.StringTrimmerEditor
 */
public class  StringEditor extends PropertyEditorSupport {

    /** 转义HTML */
//    private boolean escapeHTML;

    /** 转义javascript */
//    private boolean escapeJavaScript;

    /** 是否将空字符串转换为null */
    private final boolean emptyAsNull;

    /** 是否去掉前后空格 */
    private final boolean trimmed;

    public StringEditor() {
        this(true, true);
    }

    public StringEditor(boolean emptyAsNull, boolean trimmed) {
        this.emptyAsNull = emptyAsNull;
        this.trimmed = trimmed;
    }

    @Override
    public String getAsText() {
        return getValue() != null ? (String) getValue() : null;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null) {
            setValue(null);
        } else if (emptyAsNull && text.isEmpty()) {
            setValue(null);
        } else if (trimmed) {
            //HTML转义（防止XSS攻击）
            //HtmlUtils.htmlEscape 默认的是ISO-8859-1编码格式，会将中文的某些符号进行转义。
            //如果不想让中文符号进行转义请使用UTF-8的编码格式。例如：HtmlUtils.htmlEscape(text.trim(), "UTF-8")
//            setValue(HtmlUtils.htmlEscape(text.trim()));
            setValue(text.trim());
        } else {
            //HTML转义（防止XSS攻击）
            //HtmlUtils.htmlEscape 默认的是ISO-8859-1编码格式，会将中文的某些符号进行转义。
            //如果不想让中文符号进行转义请使用UTF-8的编码格式。例如：HtmlUtils.htmlEscape(text, "UTF-8")
//            setValue(HtmlUtils.htmlEscape(text));
//            setValue(JavaScriptUtils.javaScriptEscape(text));
            setValue(text);
        }
    }
}
