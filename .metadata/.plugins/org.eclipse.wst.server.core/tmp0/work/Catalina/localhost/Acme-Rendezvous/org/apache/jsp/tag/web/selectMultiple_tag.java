package org.apache.jsp.tag.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class selectMultiple_tag
    extends javax.servlet.jsp.tagext.SimpleTagSupport
    implements org.apache.jasper.runtime.JspSourceDependent {


  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.List<java.lang.String> _jspx_dependants;

  private javax.servlet.jsp.JspContext jspContext;
  private java.io.Writer _jspx_sout;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fjstl_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fjstl_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005flabel_0026_005fpath;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005fselect_0026_005fpath_005fonchange_005fmultiple_005fid;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005foption_0026_005fvalue_005flabel_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005foptions_0026_005fitems_005fitemValue_005fitemLabel_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fform_005ferrors_0026_005fpath_005fcssClass_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public void setJspContext(javax.servlet.jsp.JspContext ctx) {
    super.setJspContext(ctx);
    java.util.ArrayList _jspx_nested = null;
    java.util.ArrayList _jspx_at_begin = null;
    java.util.ArrayList _jspx_at_end = null;
    this.jspContext = new org.apache.jasper.runtime.JspContextWrapper(ctx, _jspx_nested, _jspx_at_begin, _jspx_at_end, null);
  }

  public javax.servlet.jsp.JspContext getJspContext() {
    return this.jspContext;
  }
  private java.lang.String path;
  private java.lang.String code;
  private java.util.Collection items;
  private java.lang.String itemLabel;
  private java.lang.String id;
  private java.lang.String onchange;

  public java.lang.String getPath() {
    return this.path;
  }

  public void setPath(java.lang.String path) {
    this.path = path;
    jspContext.setAttribute("path", path);
  }

  public java.lang.String getCode() {
    return this.code;
  }

  public void setCode(java.lang.String code) {
    this.code = code;
    jspContext.setAttribute("code", code);
  }

  public java.util.Collection getItems() {
    return this.items;
  }

  public void setItems(java.util.Collection items) {
    this.items = items;
    jspContext.setAttribute("items", items);
  }

  public java.lang.String getItemLabel() {
    return this.itemLabel;
  }

  public void setItemLabel(java.lang.String itemLabel) {
    this.itemLabel = itemLabel;
    jspContext.setAttribute("itemLabel", itemLabel);
  }

  public java.lang.String getId() {
    return this.id;
  }

  public void setId(java.lang.String id) {
    this.id = id;
    jspContext.setAttribute("id", id);
  }

  public java.lang.String getOnchange() {
    return this.onchange;
  }

  public void setOnchange(java.lang.String onchange) {
    this.onchange = onchange;
    jspContext.setAttribute("onchange", onchange);
  }

  public java.util.List<java.lang.String> getDependants() {
    return _jspx_dependants;
  }

  private void _jspInit(javax.servlet.ServletConfig config) {
    _005fjspx_005ftagPool_005fjstl_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _005fjspx_005ftagPool_005fjstl_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _005fjspx_005ftagPool_005fform_005flabel_0026_005fpath = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _005fjspx_005ftagPool_005fform_005fselect_0026_005fpath_005fonchange_005fmultiple_005fid = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _005fjspx_005ftagPool_005fform_005foption_0026_005fvalue_005flabel_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _005fjspx_005ftagPool_005fform_005foptions_0026_005fitems_005fitemValue_005fitemLabel_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _005fjspx_005ftagPool_005fform_005ferrors_0026_005fpath_005fcssClass_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(config);
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(config.getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(config);
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fjstl_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fjstl_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fform_005flabel_0026_005fpath.release();
    _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody.release();
    _005fjspx_005ftagPool_005fform_005fselect_0026_005fpath_005fonchange_005fmultiple_005fid.release();
    _005fjspx_005ftagPool_005fform_005foption_0026_005fvalue_005flabel_005fnobody.release();
    _005fjspx_005ftagPool_005fform_005foptions_0026_005fitems_005fitemValue_005fitemLabel_005fnobody.release();
    _005fjspx_005ftagPool_005fform_005ferrors_0026_005fpath_005fcssClass_005fnobody.release();
  }

  public void doTag() throws javax.servlet.jsp.JspException, java.io.IOException {
    javax.servlet.jsp.PageContext _jspx_page_context = (javax.servlet.jsp.PageContext)jspContext;
    javax.servlet.http.HttpServletRequest request = (javax.servlet.http.HttpServletRequest) _jspx_page_context.getRequest();
    javax.servlet.http.HttpServletResponse response = (javax.servlet.http.HttpServletResponse) _jspx_page_context.getResponse();
    javax.servlet.http.HttpSession session = _jspx_page_context.getSession();
    javax.servlet.ServletContext application = _jspx_page_context.getServletContext();
    javax.servlet.ServletConfig config = _jspx_page_context.getServletConfig();
    javax.servlet.jsp.JspWriter out = jspContext.getOut();
    _jspInit(config);
    jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,jspContext);
    if( getPath() != null ) 
      _jspx_page_context.setAttribute("path", getPath());
    if( getCode() != null ) 
      _jspx_page_context.setAttribute("code", getCode());
    if( getItems() != null ) 
      _jspx_page_context.setAttribute("items", getItems());
    if( getItemLabel() != null ) 
      _jspx_page_context.setAttribute("itemLabel", getItemLabel());
    if( getId() != null ) 
      _jspx_page_context.setAttribute("id", getId());
    if( getOnchange() != null ) 
      _jspx_page_context.setAttribute("onchange", getOnchange());

    try {
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_jstl_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      if (_jspx_meth_jstl_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div>\r\n");
      out.write("\t");
      if (_jspx_meth_form_005flabel_005f0(_jspx_page_context))
        return;
      out.write("\t\r\n");
      out.write("\t");
      //  form:select
      org.springframework.web.servlet.tags.form.SelectTag _jspx_th_form_005fselect_005f0 = (org.springframework.web.servlet.tags.form.SelectTag) _005fjspx_005ftagPool_005fform_005fselect_0026_005fpath_005fonchange_005fmultiple_005fid.get(org.springframework.web.servlet.tags.form.SelectTag.class);
      _jspx_th_form_005fselect_005f0.setPageContext(_jspx_page_context);
      _jspx_th_form_005fselect_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) this ));      // /WEB-INF/tags/selectMultiple.tag(47,1) name = id type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fselect_005f0.setId((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${id}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
      // /WEB-INF/tags/selectMultiple.tag(47,1) name = multiple type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fselect_005f0.setMultiple(new String("true"));
      // /WEB-INF/tags/selectMultiple.tag(47,1) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fselect_005f0.setPath((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${path}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
      // /WEB-INF/tags/selectMultiple.tag(47,1) name = onchange type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005fselect_005f0.setOnchange((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${onchange}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
      int[] _jspx_push_body_count_form_005fselect_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_form_005fselect_005f0 = _jspx_th_form_005fselect_005f0.doStartTag();
        if (_jspx_eval_form_005fselect_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\t");
            //  form:option
            org.springframework.web.servlet.tags.form.OptionTag _jspx_th_form_005foption_005f0 = (org.springframework.web.servlet.tags.form.OptionTag) _005fjspx_005ftagPool_005fform_005foption_0026_005fvalue_005flabel_005fnobody.get(org.springframework.web.servlet.tags.form.OptionTag.class);
            _jspx_th_form_005foption_005f0.setPageContext(_jspx_page_context);
            _jspx_th_form_005foption_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fselect_005f0);
            // /WEB-INF/tags/selectMultiple.tag(48,2) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_form_005foption_005f0.setValue(new String("0"));
            // /WEB-INF/tags/selectMultiple.tag(48,2) name = label type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_form_005foption_005f0.setLabel("----");
            int[] _jspx_push_body_count_form_005foption_005f0 = new int[] { 0 };
            try {
              int _jspx_eval_form_005foption_005f0 = _jspx_th_form_005foption_005f0.doStartTag();
              if (_jspx_th_form_005foption_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
                throw new javax.servlet.jsp.SkipPageException();
              }
            } catch (java.lang.Throwable _jspx_exception) {
              while (_jspx_push_body_count_form_005foption_005f0[0]-- > 0)
                out = _jspx_page_context.popBody();
              _jspx_th_form_005foption_005f0.doCatch(_jspx_exception);
            } finally {
              _jspx_th_form_005foption_005f0.doFinally();
              _005fjspx_005ftagPool_005fform_005foption_0026_005fvalue_005flabel_005fnobody.reuse(_jspx_th_form_005foption_005f0);
            }
            out.write("\t\t\r\n");
            out.write("\t\t");
            if (_jspx_meth_form_005foptions_005f0(_jspx_th_form_005fselect_005f0, _jspx_page_context, _jspx_push_body_count_form_005fselect_005f0))
              return;
            out.write('\r');
            out.write('\n');
            out.write('	');
            int evalDoAfterBody = _jspx_th_form_005fselect_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_form_005fselect_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          throw new javax.servlet.jsp.SkipPageException();
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_form_005fselect_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_form_005fselect_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_form_005fselect_005f0.doFinally();
        _005fjspx_005ftagPool_005fform_005fselect_0026_005fpath_005fonchange_005fmultiple_005fid.reuse(_jspx_th_form_005fselect_005f0);
      }
      out.write('\r');
      out.write('\n');
      out.write('	');
      //  form:errors
      org.springframework.web.servlet.tags.form.ErrorsTag _jspx_th_form_005ferrors_005f0 = (org.springframework.web.servlet.tags.form.ErrorsTag) _005fjspx_005ftagPool_005fform_005ferrors_0026_005fpath_005fcssClass_005fnobody.get(org.springframework.web.servlet.tags.form.ErrorsTag.class);
      _jspx_th_form_005ferrors_005f0.setPageContext(_jspx_page_context);
      _jspx_th_form_005ferrors_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) this ));      // /WEB-INF/tags/selectMultiple.tag(51,1) name = path type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005ferrors_005f0.setPath((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${path}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
      // /WEB-INF/tags/selectMultiple.tag(51,1) name = cssClass type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_form_005ferrors_005f0.setCssClass("error");
      int[] _jspx_push_body_count_form_005ferrors_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_form_005ferrors_005f0 = _jspx_th_form_005ferrors_005f0.doStartTag();
        if (_jspx_th_form_005ferrors_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          throw new javax.servlet.jsp.SkipPageException();
        }
      } catch (java.lang.Throwable _jspx_exception) {
        while (_jspx_push_body_count_form_005ferrors_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_form_005ferrors_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_form_005ferrors_005f0.doFinally();
        _005fjspx_005ftagPool_005fform_005ferrors_0026_005fpath_005fcssClass_005fnobody.reuse(_jspx_th_form_005ferrors_005f0);
      }
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
    } catch( java.lang.Throwable t ) {
      if( t instanceof javax.servlet.jsp.SkipPageException )
          throw (javax.servlet.jsp.SkipPageException) t;
      if( t instanceof java.io.IOException )
          throw (java.io.IOException) t;
      if( t instanceof java.lang.IllegalStateException )
          throw (java.lang.IllegalStateException) t;
      if( t instanceof javax.servlet.jsp.JspException )
          throw (javax.servlet.jsp.JspException) t;
      throw new javax.servlet.jsp.JspException(t);
    } finally {
      jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,super.getJspContext());
      ((org.apache.jasper.runtime.JspContextWrapper) jspContext).syncEndTagFile();
      _jspDestroy();
    }
  }

  private boolean _jspx_meth_jstl_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  jstl:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_jstl_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fjstl_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_jstl_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_jstl_005fif_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) this ));    // /WEB-INF/tags/selectMultiple.tag(33,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_jstl_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${id == null}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_jstl_005fif_005f0 = _jspx_th_jstl_005fif_005f0.doStartTag();
    if (_jspx_eval_jstl_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_jstl_005fset_005f0(_jspx_th_jstl_005fif_005f0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_jstl_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_jstl_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjstl_005fif_0026_005ftest.reuse(_jspx_th_jstl_005fif_005f0);
      throw new javax.servlet.jsp.SkipPageException();
    }
    _005fjspx_005ftagPool_005fjstl_005fif_0026_005ftest.reuse(_jspx_th_jstl_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_jstl_005fset_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_jstl_005fif_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  jstl:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_jstl_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fjstl_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_jstl_005fset_005f0.setPageContext(_jspx_page_context);
    _jspx_th_jstl_005fset_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_jstl_005fif_005f0);
    // /WEB-INF/tags/selectMultiple.tag(34,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_jstl_005fset_005f0.setVar("id");
    // /WEB-INF/tags/selectMultiple.tag(34,1) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_jstl_005fset_005f0.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/tags/selectMultiple.tag(34,1) '${UUID.randomUUID().toString()}'",_el_expressionfactory.createValueExpression(this.getJspContext().getELContext(),"${UUID.randomUUID().toString()}",java.lang.Object.class)).getValue(this.getJspContext().getELContext()));
    int _jspx_eval_jstl_005fset_005f0 = _jspx_th_jstl_005fset_005f0.doStartTag();
    if (_jspx_th_jstl_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjstl_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_jstl_005fset_005f0);
      throw new javax.servlet.jsp.SkipPageException();
    }
    _005fjspx_005ftagPool_005fjstl_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_jstl_005fset_005f0);
    return false;
  }

  private boolean _jspx_meth_jstl_005fif_005f1(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  jstl:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_jstl_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fjstl_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_jstl_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_jstl_005fif_005f1.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) this ));    // /WEB-INF/tags/selectMultiple.tag(37,0) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_jstl_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${onchange == null}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false)).booleanValue());
    int _jspx_eval_jstl_005fif_005f1 = _jspx_th_jstl_005fif_005f1.doStartTag();
    if (_jspx_eval_jstl_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_jstl_005fset_005f1(_jspx_th_jstl_005fif_005f1, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_jstl_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_jstl_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjstl_005fif_0026_005ftest.reuse(_jspx_th_jstl_005fif_005f1);
      throw new javax.servlet.jsp.SkipPageException();
    }
    _005fjspx_005ftagPool_005fjstl_005fif_0026_005ftest.reuse(_jspx_th_jstl_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_jstl_005fset_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_jstl_005fif_005f1, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  jstl:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_jstl_005fset_005f1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fjstl_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_jstl_005fset_005f1.setPageContext(_jspx_page_context);
    _jspx_th_jstl_005fset_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_jstl_005fif_005f1);
    // /WEB-INF/tags/selectMultiple.tag(38,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_jstl_005fset_005f1.setVar("onchange");
    // /WEB-INF/tags/selectMultiple.tag(38,1) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_jstl_005fset_005f1.setValue(new org.apache.jasper.el.JspValueExpression("/WEB-INF/tags/selectMultiple.tag(38,1) 'javascript: return true;'",_el_expressionfactory.createValueExpression("javascript: return true;",java.lang.Object.class)).getValue(this.getJspContext().getELContext()));
    int _jspx_eval_jstl_005fset_005f1 = _jspx_th_jstl_005fset_005f1.doStartTag();
    if (_jspx_th_jstl_005fset_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fjstl_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_jstl_005fset_005f1);
      throw new javax.servlet.jsp.SkipPageException();
    }
    _005fjspx_005ftagPool_005fjstl_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_jstl_005fset_005f1);
    return false;
  }

  private boolean _jspx_meth_form_005flabel_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  form:label
    org.springframework.web.servlet.tags.form.LabelTag _jspx_th_form_005flabel_005f0 = (org.springframework.web.servlet.tags.form.LabelTag) _005fjspx_005ftagPool_005fform_005flabel_0026_005fpath.get(org.springframework.web.servlet.tags.form.LabelTag.class);
    _jspx_th_form_005flabel_005f0.setPageContext(_jspx_page_context);
    _jspx_th_form_005flabel_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) this ));    // /WEB-INF/tags/selectMultiple.tag(44,1) name = path type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005flabel_005f0.setPath((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${path}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
    int[] _jspx_push_body_count_form_005flabel_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_form_005flabel_005f0 = _jspx_th_form_005flabel_005f0.doStartTag();
      if (_jspx_eval_form_005flabel_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t");
          if (_jspx_meth_spring_005fmessage_005f0(_jspx_th_form_005flabel_005f0, _jspx_page_context, _jspx_push_body_count_form_005flabel_005f0))
            return true;
          out.write('\r');
          out.write('\n');
          out.write('	');
          int evalDoAfterBody = _jspx_th_form_005flabel_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_form_005flabel_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        throw new javax.servlet.jsp.SkipPageException();
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_005flabel_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_005flabel_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_005flabel_005f0.doFinally();
      _005fjspx_005ftagPool_005fform_005flabel_0026_005fpath.reuse(_jspx_th_form_005flabel_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_spring_005fmessage_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005flabel_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005flabel_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  spring:message
    org.springframework.web.servlet.tags.MessageTag _jspx_th_spring_005fmessage_005f0 = (org.springframework.web.servlet.tags.MessageTag) _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody.get(org.springframework.web.servlet.tags.MessageTag.class);
    _jspx_th_spring_005fmessage_005f0.setPageContext(_jspx_page_context);
    _jspx_th_spring_005fmessage_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005flabel_005f0);
    // /WEB-INF/tags/selectMultiple.tag(45,2) name = code type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005fmessage_005f0.setCode((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${code}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
    int[] _jspx_push_body_count_spring_005fmessage_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_spring_005fmessage_005f0 = _jspx_th_spring_005fmessage_005f0.doStartTag();
      if (_jspx_th_spring_005fmessage_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        throw new javax.servlet.jsp.SkipPageException();
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_spring_005fmessage_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_spring_005fmessage_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_spring_005fmessage_005f0.doFinally();
      _005fjspx_005ftagPool_005fspring_005fmessage_0026_005fcode_005fnobody.reuse(_jspx_th_spring_005fmessage_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_form_005foptions_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_form_005fselect_005f0, javax.servlet.jsp.PageContext _jspx_page_context, int[] _jspx_push_body_count_form_005fselect_005f0)
          throws java.lang.Throwable {
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  form:options
    org.springframework.web.servlet.tags.form.OptionsTag _jspx_th_form_005foptions_005f0 = (org.springframework.web.servlet.tags.form.OptionsTag) _005fjspx_005ftagPool_005fform_005foptions_0026_005fitems_005fitemValue_005fitemLabel_005fnobody.get(org.springframework.web.servlet.tags.form.OptionsTag.class);
    _jspx_th_form_005foptions_005f0.setPageContext(_jspx_page_context);
    _jspx_th_form_005foptions_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_form_005fselect_005f0);
    // /WEB-INF/tags/selectMultiple.tag(49,2) name = items type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005foptions_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${items}", java.lang.Object.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
    // /WEB-INF/tags/selectMultiple.tag(49,2) name = itemValue type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005foptions_005f0.setItemValue("id");
    // /WEB-INF/tags/selectMultiple.tag(49,2) name = itemLabel type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_form_005foptions_005f0.setItemLabel((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${itemLabel}", java.lang.String.class, (javax.servlet.jsp.PageContext)this.getJspContext(), null, false));
    int[] _jspx_push_body_count_form_005foptions_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_form_005foptions_005f0 = _jspx_th_form_005foptions_005f0.doStartTag();
      if (_jspx_th_form_005foptions_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        throw new javax.servlet.jsp.SkipPageException();
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_form_005foptions_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_form_005foptions_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_form_005foptions_005f0.doFinally();
      _005fjspx_005ftagPool_005fform_005foptions_0026_005fitems_005fitemValue_005fitemLabel_005fnobody.reuse(_jspx_th_form_005foptions_005f0);
    }
    return false;
  }
}
