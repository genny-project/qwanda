package life.genny.test;

import org.junit.Test;

import life.genny.qwanda.QwandaVersion;

public class VersionTest {
	@Test
	public void versionTest()
	{
		System.out.println("Version:\t"+QwandaVersion.getVersion());
		System.out.println("Build:  \t"+QwandaVersion.getBuildDate());
		System.out.println("Commit: \t"+QwandaVersion.getCommitDate());
		
		System.out.println("----------------------------------------\n"+QwandaVersion.getJson());
	}
}
