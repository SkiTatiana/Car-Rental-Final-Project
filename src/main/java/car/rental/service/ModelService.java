package car.rental.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import car.rental.controller.model.ModelData;
import car.rental.dao.ModelDao;
import car.rental.entity.Model;

@Service
public class ModelService {

	@Autowired
	private ModelDao modelDao;

	@Transactional(readOnly = false)
	public ModelData saveModel(ModelData modelData) {
		Long modelId = modelData.getModelId();
		Model model = findOrCreateModel(modelId);

		setFieldsInModel(model, modelData);
		return new ModelData(modelDao.save(model));
	}

	private void setFieldsInModel(Model model, ModelData modelData) {
		// model.setModelId(modelData.getModelId);
		model.setMake(modelData.getMake());
		model.setModelName(modelData.getModelName());
		model.setNumSeats(modelData.getNumSeats());
		model.setVehicleClass(modelData.getVehicleClass());
		model.setVehicleType(modelData.getVehicleType());

	}

	private Model findModelById(Long ModelId) {
		return modelDao.findById(ModelId).orElseThrow();
	}

	private Model findOrCreateModel(Long modelId) {
		Model model;

		if (Objects.isNull(modelId)) {
			model = new Model();
		} else {
			model = findModelById(modelId);
		}
		return model;
	}

	@Transactional(readOnly = true)
	public List<ModelData> retrieveAllModels() {
		List<Model> models = modelDao.findAll();
		List<ModelData> response = new LinkedList<ModelData>();

		for (Model model : models) {
			response.add(new ModelData(model));
		}

		return response;
	}

	@Transactional(readOnly = true)
	public ModelData retrieveModelById(Long modelId) {
		Model model = findModelById(modelId);
		return new ModelData(model);
	}

	@Transactional(readOnly = false)
	public void deleteModelById(Long modelId) {
		Model model = findModelById(modelId);
		modelDao.delete(model);
	}



}
