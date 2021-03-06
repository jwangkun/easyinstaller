package com.jianglibo.vaadin.dashboard.unused;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Strings;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jianglibo.vaadin.dashboard.annotation.VaadinFormFieldWrapper;
import com.jianglibo.vaadin.dashboard.annotation.VaadinTableWrapper;
import com.jianglibo.vaadin.dashboard.domain.Domains;
import com.jianglibo.vaadin.dashboard.domain.Software;
import com.jianglibo.vaadin.dashboard.repositories.BoxRepository;
import com.jianglibo.vaadin.dashboard.repositories.PersonRepository;
import com.jianglibo.vaadin.dashboard.uicomponent.form.FormBase;
import com.jianglibo.vaadin.dashboard.uicomponent.form.FormBase.HandMakeFieldsListener;
import com.jianglibo.vaadin.dashboard.uicomponent.form.FormBase.PropertyIdAndField;
import com.jianglibo.vaadin.dashboard.uicomponent.gridfield.StepRunGridField;
import com.jianglibo.vaadin.dashboard.uifactory.FieldFactories;
import com.jianglibo.vaadin.dashboard.util.ItemViewFragmentBuilder;
import com.jianglibo.vaadin.dashboard.util.MsgUtil;
import com.jianglibo.vaadin.dashboard.util.StyleUtil;
import com.jianglibo.vaadin.dashboard.view.BaseEditView;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


@SpringView(name = InstallEditView.VIEW_NAME)
public class InstallEditView  extends BaseEditView<Install, FormBase<Install>, JpaRepository<Install,Long>>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(InstallEditView.class);
	
	

	public static final String VIEW_NAME = InstallListView.VIEW_NAME + "/edit";

	public static final FontAwesome ICON_VALUE = FontAwesome.FILE_ARCHIVE_O;
    
    
    private StepRunGridField stepRunGridField;
    
    private final StepRunRepository stepRunRepository;
    
    private final PersonRepository personRepository;
    
	@SuppressWarnings("serial")
	@Autowired
	public InstallEditView(PersonRepository personRepository, InstallRepository repository,StepRunRepository stepRunRepository, MessageSource messageSource,Domains domains,FieldFactories fieldFactories,
			ApplicationContext applicationContext) {
		super(messageSource,Install.class, domains, fieldFactories, repository);
		this.stepRunRepository = stepRunRepository;
		this.personRepository = personRepository;
		delayCreateContent();
//		this.messageSource = messageSource;
//		this.applicationContext = applicationContext;
//		this.repository= repository;
//		this.eventBus = new EventBus(this.getClass().getName());
//		eventBus.register(this);
//		setSizeFull();
//		addStyleName("transactions");
//		StyleUtil.setOverflowAuto(this, true);
//		setMargin(true);
//		
////		header = applicationContext.getBean(HeaderLayout.class).afterInjection(eventBus,false, true, "");
//		
//		addComponent(header);
////		form = (InstallForm) applicationContext.getBean(InstallForm.class).afterInjection(eventBus, this);
//		
//		Optional<PropertyIdAndField> cbop = form.getFields().stream().filter(f -> {
//			return f.getPropertyId().equals("software");
//		}).findAny();
//		
//		ComboBox cb = (ComboBox) cbop.get().getField();
//		
//		cb.addValueChangeListener(new ValueChangeListener() {
//			@Override
//			public void valueChange(ValueChangeEvent event) {
//				Notification.show(event.getProperty().getValue().toString());
//				List<StepRun> steps = ((Software)event.getProperty().getValue()).getOrderedStepDefines().stream().map(osd -> new StepRun(osd)).collect(Collectors.toList());
//				stepRunGridField.setValue(steps);
//			}
//		});
//		addComponent(form);
//		Component ft = buildFooter();
//		addComponent(ft);
//		setComponentAlignment(form, Alignment.TOP_LEFT);
//		setExpandRatio(form, 1);
	}
	
//    @SuppressWarnings("serial")
//	private Component buildFooter() {
//        HorizontalLayout footer = new HorizontalLayout();
//        footer.setWidth(100.0f, Unit.PERCENTAGE);
//
//        Button ok = new Button(messageSource.getMessage("shared.btn.save", null, UI.getCurrent().getLocale()));
//        ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
//        ok.addClickListener(new ClickListener() {
//            @Override
//            public void buttonClick(ClickEvent event) {
//            	form.save();
//            }
//        });
//        ok.focus();
//        footer.addComponent(ok);
//        footer.setComponentAlignment(ok, Alignment.TOP_RIGHT);
//        return footer;
//    }
    

	@Override
	public void detach() {
		super.detach();
		// A new instance of TransactionsView is created every time it's
		// navigated to so we'll need to clean up references to it on detach.
		// DashboardEventBus.unregister(this);
	}
	
//	@Subscribe
//	public void onBackBtnClicked(HistoryBackEvent hbe) {
//		String bu = ifb.getPreviousView();
//		if (Strings.isNullOrEmpty(bu)) {
//			bu = InstallListView.VIEW_NAME;
//		}
//		UI.getCurrent().getNavigator().navigateTo(bu);
//	}

	@Override
	public void enter(ViewChangeEvent event) {
//		LOGGER.info("parameter string is: {}", event.getParameters());
//		ifb = new ItemViewFragmentBuilder(event);
//		long bid = ifb.getBeanId();
//		if (bid == 0) {
//			bean = new Install();
//			header.setLabelTxt(MsgUtil.getViewMsg(messageSource, Install.class.getSimpleName() + ".newtitle"));
//		} else {
//			bean = repository.findOne(bid);
//			header.setLabelTxt(bean.getSoftware().getDisplayName());
//		}
//        form.setItemDataSource(bean);
	}

	@Override
	public Field<?> createField(VaadinTableWrapper vtw, VaadinFormFieldWrapper vffw) {
		stepRunGridField =  new StepRunGridField(getDomains(), getMessageSource(),vtw, vffw);
		return stepRunGridField;
	}

	@Override
	protected FormBase<Install> createForm(MessageSource messageSource, Domains domains, FieldFactories fieldFactories,
			JpaRepository<Install, Long> repository,HandMakeFieldsListener handMakeFieldsListener) {
		return new InstallForm(personRepository, getMessageSource(), getDomains(), fieldFactories, (InstallRepository) repository, stepRunRepository, handMakeFieldsListener);
	}

	@Override
	protected Install createNewBean() {
		return new Install();
	}

	@Override
	protected String getListViewName() {
		return VIEW_NAME;
	}
}
