package Main_Menu;

public class Question 
{
	int id;
	String questionText;
	String[] options;
	int correctOption;


	public Question(int id, String questionText, String[] options, int correctOption) {
	this.id = id;
	this.questionText = questionText;
	this.options = options;
	this.correctOption = correctOption;
	}

}
