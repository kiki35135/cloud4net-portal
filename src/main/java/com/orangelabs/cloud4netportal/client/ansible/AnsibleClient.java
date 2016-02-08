package com.orangelabs.cloud4netportal.client.ansible;



/*    SSH (philippe/cloud4net) ip 10.194.60.222



                Les commandes à lancer sous le répertoire cd /Ansible_alu5/:



                pour déployer les 2 vPGW sur les 2 noeuds :



                               attendre le retour de Philippe pour connaitre la commande correct J



                en attendant pour déployer une vPGW sur le  nœud de Lannion :



                               ansible-playbook --limit @hosts-Lannion multisite.yml



                en attendant pour déployer une vPGW sur le  nœud de Cévennes :



                                ansible-playbook --limit @hosts-cvn multisite.yml





                pour effacer une vPGW sur le  nœud de Lannion :



                                ansible-playbook –limit ctcm vepc-clean.yml



                pour effacer un vPGW sur le nœud de Cévennes



                                ansible-playbook –limit ctcm-cvn  vepc-clean.yml

 */

import com.jcraft.jsch.*;  

import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class  AnsibleClient {


	  // Define the logger object for this class
	  private final Logger log = LoggerFactory.getLogger(this.getClass());
	private String endLineStr = " # "; // it is dependant to the server  
	private String host = "10.194.60.222"; // host IP  
	private String user = "philippe"; // username for SSH connection  
	private String pwd = "cloud4net"; // password for SSH connection  
	private int port = 22; // default SSH port 

	// this class implements jsch UserInfo interface for passing password to the session  
	static class SSHUserInfo implements UserInfo {  
		private String password;  

		SSHUserInfo(String password) {  
			this.password = password;  
		}  

		public String getPassphrase() {  
			return null;  
		}  

		public String getPassword() {  
			return password;  
		}  

		public boolean promptPassword(String arg0) {  
			return true;  
		}  

		public boolean promptPassphrase(String arg0) {  
			return true;  
		}  

		public boolean promptYesNo(String arg0) {  
			return true;  
		}  

		public void showMessage(String arg0) {  
			System.out.println(arg0);  
		}  
	}  



	private String FOLDER="~/ansible_alu5/";

	public AnsibleClient()
	{
		this.host="127.0.0.1";
		this.port=22;
		this.user="rjtl2676";
		this.pwd="christophe";
	}
	
	public AnsibleClient( String host, int port, String user, String pwd)
	{
		this.host=host;
		this.port=port;
		this.user=user;
		this.pwd=pwd;
	}

	
	public boolean deployPGW(String name,String filename){
		JSch shell = new JSch();
		try {
			log.info("Start Session with "+ host );
			// get a new session    
			Session session = shell.getSession(user, host, port);  

			// set user password and connect to a channel  
			session.setUserInfo(new SSHUserInfo(pwd));  
			 System.out.println("Connecting SSH to " + host + " - Please wait for few seconds... ");
			//session.connect();
		      session.connect();   // making a connection with timeout.
			Channel channel = session.openChannel("exec");
			
			// send ls command to the server  
				String cmd = new String("cd ansible && ansible-playbook --limit ");
				cmd =cmd.concat(name).concat(" ").concat(filename);
				System.out.println("command : " + cmd);	
						
			   ((ChannelExec)channel).setCommand(cmd);
	            channel.setInputStream(null);
	            ((ChannelExec)channel).setErrStream(System.err);
	             
	            InputStream in=channel.getInputStream();
	            channel.connect();
	            byte[] tmp=new byte[1024];
	            while(true){
	              while(in.available()>0){
	                int i=in.read(tmp, 0, 1024);
	                if(i<0)break;
	                System.out.print(new String(tmp, 0, i));
	              }
	              if(channel.isClosed()){
	                System.out.println("exit-status: "+channel.getExitStatus());
	                break;
	              }
	              try{Thread.sleep(1000);}catch(Exception ee){}
	            }

		
			log.info("Disconnecting" );
			channel.disconnect();  
			session.disconnect();
			log.info("Disconnected" );
			return true;
		}
		catch(JSchException e)
		{
			//e.getMessage();
			log.info(e.getMessage() );
			log.info("Error message" );
			return false;

		} catch (Exception e )
		{
			log.info(e.getMessage() );
			log.info("Error message #2" );
			//	System.println(e.getMessage());
			return false;
		}



	}	


	

	public boolean testSSHConnection(){
		JSch shell = new JSch();
		try {
			log.info("Start Session with "+ host );
			// get a new session    
			Session session = shell.getSession(user, host, port);  

			// set user password and connect to a channel  
			session.setUserInfo(new SSHUserInfo(pwd));  
			 System.out.println("Connecting SSH to " + host + " - Please wait for few seconds... ");
			//session.connect();
		      session.connect();   // making a connection with timeout.
			Channel channel = session.openChannel("exec");
			

			   ((ChannelExec)channel).setCommand("ls -ltr");
	            channel.setInputStream(null);
	            ((ChannelExec)channel).setErrStream(System.err);
	             
	            InputStream in=channel.getInputStream();
	            channel.connect();
	            byte[] tmp=new byte[1024];
	            while(true){
	              while(in.available()>0){
	                int i=in.read(tmp, 0, 1024);
	                if(i<0)break;
	                System.out.print(new String(tmp, 0, i));
	              }
	              if(channel.isClosed()){
	                System.out.println("exit-status: "+channel.getExitStatus());
	                break;
	              }
	              try{Thread.sleep(1000);}catch(Exception ee){}
	            }

		
			channel.disconnect();  
			session.disconnect();
			log.info("Disconnected" );
			return true;
		}
		catch(JSchException e)
		{
			//e.getMessage();
			log.info(e.getMessage() );
			log.info("Error message" );
			return false;

		} catch (Exception e )
		{
			log.info(e.getMessage() );
			log.info("Error message #2" );
			//	System.println(e.getMessage());
			return false;
		}



	}	

	public boolean detroyPGW(String name,String filename) {
		JSch shell = new JSch();
		try {
			log.info("Start Session with "+ host );
			// get a new session    
			Session session = shell.getSession(user, host, port);  

			// set user password and connect to a channel  
			session.setUserInfo(new SSHUserInfo(pwd));  
			 System.out.println("Connecting SSH to " + host + " - Please wait for few seconds... ");
			//session.connect();
		      session.connect();   // making a connection with timeout.
			Channel channel = session.openChannel("exec");
			
			// send ls command to the server  
				String cmd = new String("cd ansible &&  ansible-playbook --limit ");
				cmd =cmd.concat(name).concat(" ").concat(filename);
				System.out.println("command : " + cmd);			
			   ((ChannelExec)channel).setCommand(cmd);
	            channel.setInputStream(null);
	            ((ChannelExec)channel).setErrStream(System.err);
	             
	            InputStream in=channel.getInputStream();
	            channel.connect();
	            byte[] tmp=new byte[1024];
	            while(true){
	              while(in.available()>0){
	                int i=in.read(tmp, 0, 1024);
	                if(i<0)break;
	                System.out.print(new String(tmp, 0, i));
	              }
	              if(channel.isClosed()){
	                System.out.println("exit-status: "+channel.getExitStatus());
	                break;
	              }
	              try{Thread.sleep(1000);}catch(Exception ee){}
	            }

		
			log.info("Disconnecting" );
			channel.disconnect();  
			session.disconnect();
			log.info("Disconnected" );
			return true;
		}
		catch(JSchException e)
		{
			//e.getMessage();
			log.info(e.getMessage() );
			log.info("Error message" );
			return false;

		} catch (Exception e )
		{
			log.info(e.getMessage() );
			log.info("Error message #2" );
			//	System.println(e.getMessage());
			return false;
		}
	}	

}