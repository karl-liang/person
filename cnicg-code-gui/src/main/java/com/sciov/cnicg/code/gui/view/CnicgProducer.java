package com.sciov.cnicg.code.gui.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTree;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sciov.cnicg.code.gui.entity.CodeDevice;
import com.sciov.cnicg.code.gui.entity.WorkPositionJob;
import com.sciov.cnicg.code.gui.service.ICodeDeviceService;
import com.sciov.cnicg.code.gui.service.impl.CodeDeviceServiceImpl;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class CnicgProducer {
	private Logger logger = LoggerFactory.getLogger(CnicgProducer.class);
	private JFrame frame;
	private JTextField allCountValueField;
	private JTextField normalValueField;
	private JTextField errCountValue;
	private JTree deviceTree;
	private JTextArea codeView;
	
	boolean startMonitor = false;
	
	ICodeDeviceService codeDeviceService =  CodeDeviceServiceImpl.getCodeDeviceService();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public void appendCodeContent(String content) {
		if(startMonitor) {
			if(codeDeviceService.updateDeviceStatus(content)) {
				String deviceName = codeDeviceService.getDeviceName(content);
				String print = String.format("%s 收到扫码设备 %s 发送的内容: %s",sdf.format(new Date()),deviceName,content );
				codeView.append(print+"\n");
				codeView.setCaretPosition(codeView.getText().length());
				
				refresh();
			}
		}
	}

	
	public static CnicgProducer producerWindows;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String deviceFile = "";
					producerWindows = new CnicgProducer(deviceFile);
					producerWindows.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					if(CnicgProducer.producerWindows!= null && CnicgProducer.producerWindows.frame.isVisible()) {
						CnicgProducer.producerWindows.appendCodeContent("A," + System.currentTimeMillis()+"\r\n");
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}).start();
	}

	/**
	 * Create the application.
	 */
	public CnicgProducer(String deviceFile) {
		codeDeviceService.setDeviceFile(deviceFile);
		
		initialize();
	}

	
	public static final String SELECT_EVENT_ADD = "+";
	private JTextField deviceNameTextField;
	private JTextField deviceIptextField;
	private JTextField targetValuetextField;
	private JTextField statusTextField;
	private JTextField infotextField;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("产线扫码");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(1);
			}
		});
		frame.setBounds(100, 100, 567, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel deviceListPanel = new JPanel();
		deviceListPanel.setBackground(SystemColor.info);
		deviceListPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		GridBagConstraints gbc_deviceListPanel = new GridBagConstraints();
		gbc_deviceListPanel.weighty = 100.0;
		gbc_deviceListPanel.weightx = 20.0;
		gbc_deviceListPanel.gridwidth = 3;
		gbc_deviceListPanel.insets = new Insets(0, 0, 0, 5);
		gbc_deviceListPanel.fill = GridBagConstraints.BOTH;
		gbc_deviceListPanel.gridx = 0;
		gbc_deviceListPanel.gridy = 0;
		frame.getContentPane().add(deviceListPanel, gbc_deviceListPanel);
		deviceListPanel.setLayout(new GridLayout(1, 1, 0, 0));
		
		deviceTree = new JTree();
		deviceTree.setForeground(SystemColor.info);
		deviceTree.setFont(new Font("宋体", Font.PLAIN, 22));
		deviceTree.setBackground(SystemColor.info);
		DefaultTreeCellRenderer treeCellRender = new DefaultTreeCellRenderer();
		treeCellRender.setTextNonSelectionColor(Color.GRAY);
		treeCellRender.setTextSelectionColor(Color.RED);
		treeCellRender.setBackgroundNonSelectionColor(SystemColor.info);
		deviceTree.setCellRenderer(treeCellRender);
		deviceTree.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == (KeyEvent.VK_DELETE)) {
					if(deviceTree.getSelectionPath() != null) {
						codeDeviceService.deleteDevice(deviceTree.getSelectionPath().getLastPathComponent().toString());
						refreshDeviceList();
					}
				}
			}
		});
		deviceTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				deviceSelectEventHandler(e);
			}
		});
		
		refreshDeviceList();
		deviceListPanel.add(deviceTree);
		
		JPanel mainPanel = new JPanel();
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.weighty = 100.0;
		gbc_mainPanel.weightx = 60.0;
		gbc_mainPanel.gridwidth = 8;
		gbc_mainPanel.insets = new Insets(0, 0, 0, 5);
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 3;
		gbc_mainPanel.gridy = 0;
		frame.getContentPane().add(mainPanel, gbc_mainPanel);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{241, 0};
		gbl_mainPanel.rowHeights = new int[] {131, 131, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JPanel staticPanel = new JPanel();
		GridBagConstraints gbc_staticPanel = new GridBagConstraints();
		gbc_staticPanel.weighty = 20.0;
		gbc_staticPanel.weightx = 100.0;
		gbc_staticPanel.fill = GridBagConstraints.BOTH;
		gbc_staticPanel.insets = new Insets(0, 0, 5, 0);
		gbc_staticPanel.gridx = 0;
		gbc_staticPanel.gridy = 0;
		mainPanel.add(staticPanel, gbc_staticPanel);
		staticPanel.setLayout(new GridLayout(4, 2, 0, 0));
		
		JPanel allCountLabelPanel = new JPanel();
		allCountLabelPanel.setBackground(Color.ORANGE);
		staticPanel.add(allCountLabelPanel);
		allCountLabelPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel allCountLabel_1 = new JLabel("总扫码数：");
		allCountLabel_1.setFont(new Font("宋体", Font.PLAIN, 22));
		allCountLabel_1.setBackground(Color.WHITE);
		allCountLabelPanel.add(allCountLabel_1);
		
		JPanel allCountValuePanel = new JPanel();
		allCountValuePanel.setBackground(Color.WHITE);
		staticPanel.add(allCountValuePanel);
		allCountValuePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		allCountValueField = new JTextField();
		allCountValueField.setFont(new Font("宋体", Font.PLAIN, 22));
		allCountValueField.setForeground(Color.BLACK);
		allCountValueField.setBackground(Color.ORANGE);
		allCountValueField.setText("0");
		allCountValuePanel.add(allCountValueField);
		allCountValueField.setColumns(10);
		
		JPanel normalCountLabelPanel = new JPanel();
		normalCountLabelPanel.setBackground(Color.ORANGE);
		staticPanel.add(normalCountLabelPanel);
		normalCountLabelPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel normalLabel = new JLabel("正常码数：");
		normalLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		normalCountLabelPanel.add(normalLabel);
		
		JPanel normalCountValuePanel = new JPanel();
		staticPanel.add(normalCountValuePanel);
		normalCountValuePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		normalValueField = new JTextField();
		normalValueField.setFont(new Font("宋体", Font.PLAIN, 22));
		normalValueField.setForeground(Color.BLACK);
		normalValueField.setBackground(Color.ORANGE);
		normalValueField.setText("0");
		normalCountValuePanel.add(normalValueField);
		normalValueField.setColumns(10);
		
		JPanel errCountLabelPanel = new JPanel();
		errCountLabelPanel.setBackground(Color.ORANGE);
		staticPanel.add(errCountLabelPanel);
		errCountLabelPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel errLable = new JLabel("异常码数：");
		errLable.setFont(new Font("宋体", Font.PLAIN, 22));
		errCountLabelPanel.add(errLable);
		
		JPanel errCountValuePanel = new JPanel();
		staticPanel.add(errCountValuePanel);
		errCountValuePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		errCountValue = new JTextField();
		errCountValue.setFont(new Font("宋体", Font.PLAIN, 22));
		errCountValue.setBackground(Color.ORANGE);
		errCountValue.setText("0");
		errCountValuePanel.add(errCountValue);
		errCountValue.setColumns(10);
		
		JPanel targetLabelPanel = new JPanel();
		targetLabelPanel.setBackground(Color.ORANGE);
		staticPanel.add(targetLabelPanel);
		targetLabelPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("目标码数：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		targetLabelPanel.add(lblNewLabel);
		
		JPanel targetValuePanel = new JPanel();
		targetValuePanel.setBackground(Color.ORANGE);
		staticPanel.add(targetValuePanel);
		targetValuePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		targetValuetextField = new JTextField();
		targetValuetextField.setText("0");
		targetValuetextField.setBackground(Color.ORANGE);
		targetValuetextField.setFont(new Font("宋体", Font.PLAIN, 22));
		targetValuePanel.add(targetValuetextField);
		targetValuetextField.setColumns(10);
		
		JPanel CodeViewPanel = new JPanel();
		CodeViewPanel.setToolTipText("");
		GridBagConstraints gbc_CodeViewPanel = new GridBagConstraints();
		gbc_CodeViewPanel.weighty = 80.0;
		gbc_CodeViewPanel.weightx = 100.0;
		gbc_CodeViewPanel.fill = GridBagConstraints.BOTH;
		gbc_CodeViewPanel.gridx = 0;
		gbc_CodeViewPanel.gridy = 1;
		mainPanel.add(CodeViewPanel, gbc_CodeViewPanel);
		CodeViewPanel.setLayout(new GridLayout(1, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		CodeViewPanel.add(scrollPane);
		
		codeView = new JTextArea();
		codeView.setBackground(Color.BLACK);
		codeView.setForeground(Color.YELLOW);
		scrollPane.setViewportView(codeView);
		
		JPanel controlPanel = new JPanel();
		GridBagConstraints gbc_controlPanel = new GridBagConstraints();
		gbc_controlPanel.weighty = 100.0;
		gbc_controlPanel.weightx = 20.0;
		gbc_controlPanel.fill = GridBagConstraints.BOTH;
		gbc_controlPanel.gridx = 11;
		gbc_controlPanel.gridy = 0;
		frame.getContentPane().add(controlPanel, gbc_controlPanel);
		GridBagLayout gbl_controlPanel = new GridBagLayout();
		gbl_controlPanel.columnWidths = new int[]{106, 0};
		gbl_controlPanel.rowHeights = new int[]{54, 54, 54, 0, 0, 0, 0, 0, 0};
		gbl_controlPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_controlPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		controlPanel.setLayout(gbl_controlPanel);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		controlPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{60, 0, 0};
		gbl_panel.rowHeights = new int[]{15, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel label = new JLabel("工位任务：");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.anchor = GridBagConstraints.NORTHWEST;
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		JLabel label_1 = new JLabel("工位名称：");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 1;
		panel.add(label_1, gbc_label_1);
		
		deviceNameTextField = new JTextField();
		GridBagConstraints gbc_deviceNameTextField = new GridBagConstraints();
		gbc_deviceNameTextField.weightx = 100.0;
		gbc_deviceNameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_deviceNameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_deviceNameTextField.gridx = 1;
		gbc_deviceNameTextField.gridy = 1;
		panel.add(deviceNameTextField, gbc_deviceNameTextField);
		deviceNameTextField.setColumns(10);
		
		JLabel lblIp = new JLabel("设备IP：");
		GridBagConstraints gbc_lblIp = new GridBagConstraints();
		gbc_lblIp.anchor = GridBagConstraints.EAST;
		gbc_lblIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp.gridx = 0;
		gbc_lblIp.gridy = 2;
		panel.add(lblIp, gbc_lblIp);
		
		deviceIptextField = new JTextField();
		GridBagConstraints gbc_deviceIptextField = new GridBagConstraints();
		gbc_deviceIptextField.insets = new Insets(0, 0, 5, 0);
		gbc_deviceIptextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_deviceIptextField.gridx = 1;
		gbc_deviceIptextField.gridy = 2;
		panel.add(deviceIptextField, gbc_deviceIptextField);
		deviceIptextField.setColumns(10);
		
		JLabel label_3 = new JLabel("状态：");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.NORTHEAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 3;
		panel.add(label_3, gbc_label_3);
		
		statusTextField = new JTextField();
		GridBagConstraints gbc_statusTextField = new GridBagConstraints();
		gbc_statusTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_statusTextField.anchor = GridBagConstraints.NORTH;
		gbc_statusTextField.insets = new Insets(0, 0, 5, 0);
		gbc_statusTextField.gridx = 1;
		gbc_statusTextField.gridy = 3;
		panel.add(statusTextField, gbc_statusTextField);
		statusTextField.setColumns(10);
		
		JLabel label_2 = new JLabel("描述：");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 0, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 4;
		panel.add(label_2, gbc_label_2);
		
		infotextField = new JTextField();
		GridBagConstraints gbc_infotextField = new GridBagConstraints();
		gbc_infotextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_infotextField.gridx = 1;
		gbc_infotextField.gridy = 4;
		panel.add(infotextField, gbc_infotextField);
		infotextField.setColumns(10);
		
		JPanel beginPanel = new JPanel();
		GridBagConstraints gbc_beginPanel = new GridBagConstraints();
		gbc_beginPanel.fill = GridBagConstraints.BOTH;
		gbc_beginPanel.insets = new Insets(0, 0, 5, 0);
		gbc_beginPanel.gridx = 0;
		gbc_beginPanel.gridy = 1;
		controlPanel.add(beginPanel, gbc_beginPanel);
		
		JButton btnBeginButton = new JButton("开始/暂停");
		btnBeginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				startMonitor = !startMonitor;
			}
		});
		beginPanel.setLayout(new BorderLayout(0, 0));
		beginPanel.add(btnBeginButton);
		
		JPanel clean = new JPanel();
		GridBagConstraints gbc_clean = new GridBagConstraints();
		gbc_clean.insets = new Insets(0, 0, 5, 0);
		gbc_clean.fill = GridBagConstraints.BOTH;
		gbc_clean.gridx = 0;
		gbc_clean.gridy = 2;
		controlPanel.add(clean, gbc_clean);
		clean.setLayout(new BorderLayout(0, 0));
		
		JButton btnCleanButton = new JButton("清除数据");
		clean.add(btnCleanButton);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.weighty = 100.0;
		gbc_panel_1.weightx = 100.0;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		controlPanel.add(panel_1, gbc_panel_1);
		btnCleanButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cleanData();
			}
		});
	}
	
	public void setEnabled(boolean b) {
		this.frame.setEnabled(b);
	}

	public void setVisible(boolean b) {
		this.frame.setVisible(b);
	}
	
	public void cleanData() {
		startMonitor = false;
		
		codeDeviceService.cleanData();
		
		startMonitor = true;
		
		refresh();
	}
	
	public void refreshDeviceList() {
		deviceTree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("工位任务:") {
					{
						List<WorkPositionJob> list = codeDeviceService.initCodeDevice();
						if(list != null && list.size() > 0) {
							for(CodeDevice cd : list) {
								add(new DefaultMutableTreeNode(cd.getName()));
							}
						}
						add(new DefaultMutableTreeNode("+"));
					}
				}
			));
	}

	private void deviceSelectEventHandler(TreeSelectionEvent e) {
		if(e.getNewLeadSelectionPath() == null) {
			codeDeviceService.setCurrentCodeDevice(null);
			return;
		}
		String value = e.getNewLeadSelectionPath().getLastPathComponent().toString();
		if(value.equals(SELECT_EVENT_ADD)) {
			AddDeviceDialog dialog = new AddDeviceDialog(this);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setAlwaysOnTop(true);
			frame.setEnabled(false);
		}else {
			codeDeviceService.setCurrentCodeDevice(value);
			setCurrentDeviceInfo(value);
			
		}
		
		refresh();
	}
	private void setCurrentDeviceInfo(String value) {
		WorkPositionJob cd = codeDeviceService.getCurrentCodeDevice();
		if(codeDeviceService.isAllCodeDevice(cd)) {
			deviceNameTextField.setText("所有设备");
			deviceIptextField.setText("");
			infotextField.setText("当前统计数据为所有设备数据");
			
		}else {
			deviceNameTextField.setText(cd.getName());
			deviceIptextField.setText(cd.getIp());
			infotextField.setText(cd.getInfo());
			if(cd.getTargetAccount()>=cd.getSuccCount()) {
				statusTextField.setText(String.format("目标数%d 大于等于 实际扫描数 %d, 正常",cd.getTargetAccount(), cd.getSuccCount()));
			}else {
				statusTextField.setText(String.format("目标数%d 小于 实际扫描数 %d, 异常正常",cd.getTargetAccount(), cd.getSuccCount()));
				statusTextField.setCaretColor(Color.RED);
			}
		}
		
	}

	private void refresh() {
		WorkPositionJob cd = codeDeviceService.getCurrentCodeDevice();
		
		allCountValueField.setText(cd.getAllCount()+"");
		normalValueField.setText(cd.getSuccCount()+"");
		errCountValue.setText(cd.getErrCount() + "");
		
		if(!codeDeviceService.isAllCodeDevice(cd)) {
			if(cd.getTargetAccount()>=cd.getSuccCount()) {
				statusTextField.setText(String.format("目标数%d 大于等于 实际扫描数 %d, 正常",cd.getTargetAccount(), cd.getSuccCount()));
			}else {
				statusTextField.setText(String.format("目标数%d 小于 实际扫描数 %d, 异常",cd.getTargetAccount(), cd.getSuccCount()));
				statusTextField.setForeground(Color.RED);
			}
		}
	}
}
