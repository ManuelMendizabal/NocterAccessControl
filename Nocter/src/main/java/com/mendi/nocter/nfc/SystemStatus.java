package com.mendi.nocter.nfc;

import com.mendi.nocter.Classes.message;
import java.util.Optional;

import lombok.Getter;
import lombok.ToString;

/**
 * This class functions as a global status class which communicates the status between the reader and the gui
 */
@ToString
public class SystemStatus {

        @Getter
        public static String nfcLastText;
    
        @Getter
        public static NFCObjectListener listener;      
        
	@Getter
	private boolean running;

	@Getter
	private boolean terminalsDetected;

	@Getter
	private boolean schedulersStarted;

	@Getter
	private boolean readerStarted;

	@Getter
	private boolean readerRunning;

	@Getter
	private boolean foundReader;

	private Optional<Runnable> onChangeAction = Optional.empty();

        public SystemStatus(){
            this.listener = null;
        }
         public interface NFCTextReadedListener {
            public void onNFCReaded(NFCTextReadedListener textReaded);
        }
        public void setNFCTagReadedListener(NFCObjectListener listener){
            this.listener = listener;
        }
        public static void NFCTagReaded(String text){
            listener.onNFCTagReaded(text);
        }
        
	public void onChange(Runnable action) {
		this.onChangeAction = Optional.ofNullable(action);
	}

	public void setRunning(boolean running) {
		this.running = running;
		onChangeAction.ifPresent(Runnable::run);
	}

	public void setTerminalsDetected(boolean terminalsDetected) {
            try{                
		this.terminalsDetected = terminalsDetected;
		onChangeAction.ifPresent(Runnable::run);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
	}

	public void setSchedulersStarted(boolean schedulersStarted) {
		this.schedulersStarted = schedulersStarted;
		onChangeAction.ifPresent(Runnable::run);
	}

	public void setReaderStarted(boolean readerStarted) {
		this.readerStarted = readerStarted;
		onChangeAction.ifPresent(Runnable::run);
	}

	public void setReaderRunning(boolean readerRunning) {
		this.readerRunning = readerRunning;
		onChangeAction.ifPresent(Runnable::run);
	}

	public void setFoundReader(boolean foundReader) {
		this.foundReader = foundReader;
	}
}
