import net.sourceforge.pmd.PMD;

/**
 * Created by remco on 9-6-17.
 */
public class Main {

	public static void main(String[] args) {


		String pathName = "D:\\OneDrive - Universiteit Twente\\Research\\Remco\\Programs\\retry";
		if(args.length>0){
			pathName=args[0];
		}

		PMD.main(new String[] {
				"-d", pathName,
				"-f", "csv",
				"-R", "rulesets/processing.xml"
		});

	}

}
