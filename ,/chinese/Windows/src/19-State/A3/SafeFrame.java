import java.awt.Frame; 
import java.awt.Label; 
import java.awt.Color; 
import java.awt.Button; 
import java.awt.TextField; 
import java.awt.TextArea; 
import java.awt.Panel; 
import java.awt.BorderLayout; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
 
public class SafeFrame extends Frame implements ActionListener, Context { 
    private TextField textClock = new TextField(60);	// ��X�{�b�ɶ� 
    private TextArea textScreen = new TextArea(10, 60);	// ��X�O������ 
    private Button buttonUse = new Button("�ϥΪ��w");	// �ϥΪ��w������ 
    private Button buttonAlarm = new Button("ĵ�a");	// ĵ�a������ 
    private Button buttonPhone = new Button("�@��q��");	// �@��q�ܪ����� 
    private Button buttonExit = new Button("����");		// ���������� 
 
    private State state = DayState.getInstance();		// �{�b���A 
 
    // �غc�l 
    public SafeFrame(String title) { 
        super(title); 
        setBackground(Color.lightGray); 
        setLayout(new BorderLayout()); 
        // ��XtextClock 
        add(textClock, BorderLayout.NORTH); 
        textClock.setEditable(false); 
        // ��XtextScreen 
        add(textScreen, BorderLayout.CENTER); 
        textScreen.setEditable(false); 
        // ������쭱�O�W 
        Panel panel = new Panel(); 
        panel.add(buttonUse); 
        panel.add(buttonAlarm); 
        panel.add(buttonPhone); 
        panel.add(buttonExit); 
        // ��X���O 
        add(panel, BorderLayout.SOUTH); 
        // ��X��e���W 
        pack(); 
        show(); 
        // �]�wť�R�O�� 
        buttonUse.addActionListener(this); 
        buttonAlarm.addActionListener(this); 
        buttonPhone.addActionListener(this); 
        buttonExit.addActionListener(this); 
    } 
    // �Y���H���U����A�h����o�� 
    public void actionPerformed(ActionEvent e) { 
        System.out.println("" + e); 
        if (e.getSource() == buttonUse) {           // �ϥΪ��w������ 
            state.doUse(this); 
        } else if (e.getSource() == buttonAlarm) {  // ĵ�a������ 
            state.doAlarm(this); 
        } else if (e.getSource() == buttonPhone) {  // �@��q�ܪ����� 
            state.doPhone(this); 
        } else if (e.getSource() == buttonExit) {   // ���������� 
            System.exit(0); 
        } else { 
            System.out.println("?"); 
        } 
    } 
    // �]�w�ɶ� 
    public void setClock(int hour) { 
        String clockstring = "�{�b�ɶ��O"; 
        if (hour < 10) { 
            clockstring += "0" + hour + ":00"; 
        } else { 
            clockstring += hour + ":00"; 
        } 
        System.out.println(clockstring); 
        textClock.setText(clockstring); 
        state.doClock(this, hour); 
    } 
    // ���A�ܤ� 
    public void changeState(State state) { 
        System.out.println("���A�w�g�q" + this.state + "�ܦ�" + state + "�C"); 
        this.state = state; 
    } 
    // �I�s�O������ 
    public void callSecurityCenter(String msg) { 
        textScreen.append("call! " + msg + "\n"); 
    } 
    // �O�����߫O�d�O�� 
    public void recordLog(String msg) { 
        textScreen.append("record ... " + msg + "\n"); 
    } 
}