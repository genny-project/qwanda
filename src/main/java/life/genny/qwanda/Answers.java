package life.genny.qwanda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Answers implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Answer> answers = new ArrayList<Answer>();

	public Answers()
	{
		
	}
	
	public Answers(List<Answer> answers) {
		super();
		this.answers = answers;
	}
	
	public void add(Answer answer)
	{
		this.answers.add(answer);
	}

	/**
	 * @return the answers
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Answers [answers=" + answers + "]";
	}
	
	
}
