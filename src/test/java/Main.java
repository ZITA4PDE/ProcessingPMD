import net.sourceforge.pmd.PMD;

/**
 * Created by remco on 9-6-17.
 */
public class Main {

	public static void main(String[] args) {
		PMD.main(new String[] {
				"-d", "/home/remco/Documenten/Research/programs/serie1/s001dir",
				"-f", "csv",
				"-R", "rulesets/processing.xml"
		});
	}

}
