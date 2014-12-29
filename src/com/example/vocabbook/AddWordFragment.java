package com.example.vocabbook;

import java.util.ArrayList;
import java.util.List;

import com.example.vocabbook.models.Word;
import com.example.vocabbook.models.WordsDAO;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class AddWordFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(
	            R.layout.fragment_addword, container, false);
		
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		WordsDAO db = new WordsDAO(getActivity());
		db.open();
		
		List<Word> allWords = db.getAllWords();
		db.close();
		
		List<String> allWordsStrings = new ArrayList<String>();
		
		// TODO: Add button "Add synonyms" to reduce db calls
		// TODO: Remove duplicates
		for(int i = 0; i < allWords.size(); i++) {
			allWordsStrings.add(allWords.get(i).getWord());
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, allWordsStrings);
		MultiAutoCompleteTextView synonymsAutoCompleteField = (MultiAutoCompleteTextView)getView().findViewById(R.id.synonymsAutoComplete);
		synonymsAutoCompleteField.setAdapter(adapter);
		synonymsAutoCompleteField.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		
		Button btn = (Button)view.findViewById(R.id.saveWordBtn);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//View test = v.findViewById(R.id.wordField);
				Spinner languageSpinner = (Spinner)getView().findViewById(R.id.languageSpinner);
				String language = languageSpinner.getSelectedItem().toString();
				String languageCode = "";
				
				try {
					languageCode = LanguageManager.getLanguageCode(language);
				}
				catch(Exception ex) {
					// TODO: Extract common AlertDialog logic
					AlertDialog.Builder messageBox = new AlertDialog.Builder(getActivity());
					messageBox.setMessage("You must choose a language");
					messageBox.setTitle(R.string.error);
					messageBox.setPositiveButton(R.string.ok,  new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub							
						}
						
					});
					messageBox.show();
				}
				
				TextView wordField = (TextView) getView().findViewById(R.id.wordField);
				String word = wordField.getText().toString();
				
				if(word.length() == 0 || word == null) {
					// Error!
					AlertDialog.Builder messageBox = new AlertDialog.Builder(getActivity());
					messageBox.setMessage("You must specify a word");
					messageBox.setTitle(R.string.error);
					messageBox.setPositiveButton(R.string.ok,  new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub							
						}
						
					});
					messageBox.show();
				}
				
				TextView meaningField = (TextView) getView().findViewById(R.id.meaningField);
				String meaning = meaningField.getText().toString();
				
				WordsDAO dao = new WordsDAO(getActivity());
				dao.open();
				dao.addWord(word, meaning, languageCode);
				dao.close();
				
				
			}
		});
	}
	

}
