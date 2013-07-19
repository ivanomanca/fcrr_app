package c4jason;
import jason.asSyntax.Literal;
import jason.environment.Environment;
import java.util.logging.Level;
import java.util.logging.Logger;
import cartago.*;
import cartago.tools.inspector.Inspector;

/**
 * Jason Environment Class enabling access to CArtAgO environments.
 * 
 * @author aricci
 *
 */
public class CartagoEnvironment extends Environment {

	private static CartagoEnvironment instance;
	private String wspName;
	private String wspAddress;
	
	private boolean local;		// an existing local node (false -> to be created, because not existing)
	private boolean remote;		// an existing remote node
	private boolean infrastructure;	// not existing, enable infrastructure service

	private String serviceType;
	private String serviceAddress;
	
	static Logger logger = Logger.getLogger(CartagoEnvironment.class.getName());

	public void init(String[] args) {
		wspName = "default";
		
		infrastructure = false;
		local = false;
		remote = false;
		serviceType = "default";
		
		if (args.length > 0){
			if (args[0].equals("local")){
				local = true;
			} else if (args[0].equals("infrastructure")){
				infrastructure = true;
			} else if (args[0].equals("remote")){
				remote = true;
			} else {
				throw new IllegalArgumentException("Unknown argument: "+args[0]+" (should be local, remote or infrastructure)");
			}

			if (local){
				if (args.length > 1){
					wspName = args[1];
				}
				if (args.length > 2){
					throw new IllegalArgumentException("Invalid arguments for local option");
				}
			} else if (remote) {
				if (args.length > 1){
					wspName = args[1];
				}
			} 
		}
		try {				
				if (!local && !remote){
					/*
					Inspector insp = new Inspector();
				    insp.start();
					CartagoService.startNode(insp.getLogger());
					*/
					CartagoService.startNode();
					//CartagoService.registerLogger("default", new cartago.util.BasicLogger());
					
					if (infrastructure){
						try {
							if (args.length > 1){
								for (int i = 1; i < args.length; i++){
									String prot = args[i];
									try {
										Literal l = Literal.parseLiteral(prot);
										if (l.getFunctor().equals("protocol")){
											String serviceType = l.getTerm(0).toString();
											String address = null;
											CartagoService.installInfrastructureLayer(serviceType);
											if (l.getArity() > 1){
												address = l.getTerm(1).toString();
											}
											if (address == null){
												CartagoService.startInfrastructureService(serviceType);
											} else {
												CartagoService.startInfrastructureService(serviceType,address);
											}
										}
									} catch (Exception ex){
										ex.printStackTrace();
									}
								}
							} else {
								CartagoService.installInfrastructureLayer("default");
								CartagoService.startInfrastructureService("default");
							}
						} catch (Exception ex){
							logger.warning("CArtAgO RMI service already installed.");
						}
					} else {
						/* 
						 * We have a node, but not reachable from the outside.
						 * However agents running on the node may want to join remote workspaces,
						 * so we need to install infrastructure service (client side)
						 */
						if (args.length > 1){
							for (int i = 1; i < args.length; i++){
								String prot = args[i];
								try {
									Literal l = Literal.parseLiteral(prot);
									if (l.getFunctor().equals("protocol")){
										String infraLayer = l.getTerm(0).toString();
										CartagoService.installInfrastructureLayer(infraLayer);
									}
								} catch (Exception ex){
									ex.printStackTrace();
								}
							}
						} else {
							CartagoService.installInfrastructureLayer("default");
						}
						
					}
				} else if (remote){			
					if (args.length > 2){
						Literal l = Literal.parseLiteral(args[2]);
						if (l.getFunctor().equals("protocol")){
							serviceType = l.getTerm(0).toString();
							CartagoService.installInfrastructureLayer(serviceType);
						} else new IllegalArgumentException("Invalid arguments for remote option");
					}					
					logger.info("Joining a remote workspace: "+wspName+"@"+wspAddress);
				}
				
		} catch (Exception ex){			
			logger.severe("CArtAgO Environment init FAILED.");
			ex.printStackTrace();
		}
		instance = this;
		logger.info("CArtAgO Environment init OK.");
		logger.setLevel(Level.SEVERE);
	}

	/**
	 * Get the instance of this environment.
	 * 
	 * @return
	 */
	public static CartagoEnvironment getInstance(){
		return instance;
	}
	
	/**
	 * Join an agent to the default workspace of the node
	 * 
	 * @param agName agent node
	 * @param arch agent arch. class
	 * @return the interface to act inside the workspace
	 * @throws Exception
	 */
	public ICartagoSession startSession(String agName, CAgentArch arch) throws Exception {
		if (wspAddress == null){
			ICartagoSession context = CartagoService.startSession(wspName,new cartago.security.AgentIdCredential(agName),arch);
			logger.info("NEW AGENT JOINED: "+agName);
			return context;
		} else {
			ICartagoSession context = CartagoService.startRemoteSession(wspName,wspAddress,serviceType, new cartago.security.AgentIdCredential(agName),arch);
			return context;
		}
	}

	@Override
	public void stop() {
		super.stop();
		if (!local && !remote){
			try {
				CartagoService.shutdownNode();
			} catch (CartagoException e) {
				e.printStackTrace();
			}
		}
	}


}

