package com.sciov.cnicg.code.gui.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sciov.cnicg.code.gui.service.ICodeDeviceService;
import com.sciov.cnicg.code.gui.service.impl.CodeDeviceServiceImpl;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class AddDeviceDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nametextField;
	private CnicgProducer parentFrame;
	
	ICodeDeviceService codeDeviceService =  CodeDeviceServiceImpl.getCodeDeviceService();
	private JTextField iptextField;
	private JTextField infotextField;
	private JTextField targetAccouttextField;
	/**
	 * Create the dialog.
	 * @param frame 
	 */
	public AddDeviceDialog(CnicgProducer parent) {
		this.parentFrame = parent;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(5, 2, 0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(1, 1, 0, 0));
			{
				JLabel lblNewLabel = new JLabel("工位标识：");
				panel.add(lblNewLabel);
			}
			{
				nametextField = new JTextField();
				panel.add(nametextField);
				nametextField.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblIp = new JLabel("ip:");
				panel.add(lblIp);
			}
			{
				iptextField = new JTextField();
				panel.add(iptextField);
				iptextField.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblNewLabel_1 = new JLabel("目标码数:");
				panel.add(lblNewLabel_1);
			}
			{
				targetAccouttextField = new JTextField();
				targetAccouttextField.setForeground(Color.BLACK);
				targetAccouttextField.setText("0");
				panel.add(targetAccouttextField);
				targetAccouttextField.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel label = new JLabel("描述：");
				panel.add(label);
			}
			{
				infotextField = new JTextField();
				panel.add(infotextField);
				infotextField.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						addWorkJob(e);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						closeDialog();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void closeDialog() {
		parentFrame.setEnabled(true);
		this.setVisible(false);
	}
	
	private void addWorkJob(MouseEvent e) {
		String deviceName = nametextField.getText();
		if(deviceName == null || deviceName.trim().length() < 1) {
			JOptionPane.showMessageDialog(null,"工位名称为空","提示",JOptionPane.PLAIN_MESSAGE);
		}else {
			String targetAccountStr = targetAccouttextField.getText();
			int targetAccountValue = 0 ;
			try {
			 targetAccountValue = Integer.parseInt(targetAccountStr);
			 if(targetAccountValue < 1) {
				 JOptionPane.showMessageDialog(null,"目标码数异常","提示",JOptionPane.PLAIN_MESSAGE);
			 }
			}catch(Exception exception) {
				JOptionPane.showMessageDialog(null,"目标码数异常","提示",JOptionPane.PLAIN_MESSAGE);
			}
			
			if(codeDeviceService.addDevice(deviceName, iptextField.getText(), infotextField.getText(),targetAccountValue)) {
				parentFrame.refreshDeviceList();
				parentFrame.setEnabled(true);
				this.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null,"工位名称重复","提示",JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
}
