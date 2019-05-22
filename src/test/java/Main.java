import net.sourceforge.pmd.PMD;

/**
 * Created by remco on 9-6-17.
 */
public class Main {

	public static void main(String[] args) {


		String pathName = "C:\\Users\\FehnkerA\\OneDrive - Universiteit Twente\\Research\\Remco\\Programs\\zita";
		if(args.length>0){
			pathName=args[0];
		}

		PMD.main(new String[] {
				"-d", pathName,
				"-f", "csv",
				"-r", "../logs/log.csv",
				"-R", "rulesets/processing.xml"
		});

	}

}
