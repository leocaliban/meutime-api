package com.meutime.manager.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneralMapper<M, I, O> {

	@Autowired
	private ModelMapper modelMapper;

	public M responseToModel(O dtoResponse, Class<M> model) {
		return modelMapper.map(dtoResponse, model);
	}

	public M requestToModel(I dtoRequest, Class<M> model) {
		return modelMapper.map(dtoRequest, model);
	}

	public I toRequestDTO(M model, Class<I> dtoRequest) {
		return modelMapper.map(model, dtoRequest);
	}

	public O toResponseDTO(M model, Class<O> dtoResponse) {
		return modelMapper.map(model, dtoResponse);
	}

	public List<O> toResponseDTOList(List<M> modelList, Class<O> dtoResponse) {
		final Class<O> dtoClass = dtoResponse;
		return modelList.stream().map(item -> this.toResponseDTO(item, dtoClass)).collect(Collectors.toList());
	}

	public void updateModel(M model, I dtoRequest) {
		modelMapper.map(dtoRequest, model);
	}

}