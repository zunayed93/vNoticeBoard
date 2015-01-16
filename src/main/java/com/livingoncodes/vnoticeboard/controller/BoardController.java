package com.livingoncodes.vnoticeboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.livingoncodes.vnoticeboard.entity.Board;
import com.livingoncodes.vnoticeboard.repository.BoardRepository;

@Controller
public class BoardController {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BoardController.class);
	
	@Autowired
	private BoardRepository boardRepository;
	
	@RequestMapping(value ="/{boardName}", method = RequestMethod.GET)
	public String showBoard(@PathVariable("boardName") String boardName, Model model) {
		LOGGER.debug("Rendering board page: " + boardName);
		
		Board board = boardRepository.findByName(boardName);
		
		if( board == null) {
			LOGGER.debug("Board not found: " + boardName);
			
			// Add the not found boardname to model so that we can use it
			// in the new form
			model.addAttribute("boardName", boardName);
			return "newboard";
		}
		
		model.addAttribute("boardName", boardName);
		model.addAttribute("boardContent", board.getContent());
		return "board";
	}


}
