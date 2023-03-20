package org.vegetablesales.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.vegetablesales.Model.Category;
import org.vegetablesales.Model.VegetableDTO;
import org.vegetablesales.Repository.CategoryRepository;
import org.vegetablesales.Repository.VegetableDTORepository;
@Service
public class VegetableMgmtServiceImpl implements IVegetableMgmtService{
	@Autowired
	private VegetableDTORepository vegetableDTORepository;
	@Autowired
	private CategoryRepository catRepo;
	@Autowired
	private Category cat;

	@Override
	public VegetableDTO addVegetableDTO(VegetableDTO veg) {
		cat =	catRepo.findByCatName(veg.getType());
		if(cat==null) {
			cat = new Category();
			cat.setCatName(veg.getType());
//			List<VegetableDTO> list = cat.getVegList();
//			list.add(veg);
//			cat.setVegList(list);
			catRepo.save(cat);
		}
//		else {
//			List<VegetableDTO> list = cat.getVegList();
//			list.add(veg);
//			cat.setVegList(list);
//			catRepo.save(cat);
//		}
		return vegetableDTORepository.save(veg);
	}

	@Override
	public VegetableDTO updateVegetableDTO(VegetableDTO veg) {
//		Optional<VegetableDTO> v = vegetableDTORepository.findById(veg.getVegId());
//		cat = catRepo.findByCatName(veg.getType());
//		Category cat2 =  catRepo.findByCatName(v.get().getType());
//		List<VegetableDTO> l = cat2.getVegList();
//		Integer i = 0;
//		VegetableDTO rem = new VegetableDTO();
//		for(VegetableDTO vege:l) {
//			if(vege.getVegId()==veg.getVegId())
//				rem = vege;
//			i++;
//		}
//		l.remove(rem);
//		cat2.setVegList(l);
//		catRepo.save(cat2);
//		if(cat==null) {
//			cat = new Category();
//			cat.setCatName(veg.getType());
//			List<VegetableDTO> list = cat.getVegList();
//			list.add(veg);
//			cat.setVegList(list);
//			catRepo.save(cat);
//		}
//		else {
//			List<VegetableDTO> list = cat.getVegList();
//			list.add(veg);
//			cat.setVegList(list);
//			catRepo.save(cat);
//		}
		return vegetableDTORepository.save(veg);
	}

	@Override
	public VegetableDTO removeVegetableDTO(Integer vegetableId) {
		Optional<VegetableDTO> opt = vegetableDTORepository.findById(vegetableId);
        if(opt.isPresent()){
            VegetableDTO veg = opt.get();
            vegetableDTORepository.delete(veg);
//            Category cat2 =  catRepo.findByCatName(veg.getType());
//    		List<VegetableDTO> l = cat2.getVegList();
//    		l.remove(veg);
//    		cat2.setVegList(l);
//    		catRepo.save(cat2);
            return veg;
        }
        return null;
	}

	@Override
	public List<VegetableDTO> viewAllVegetables() {
		return vegetableDTORepository.findAll();
	}

	@Override
	public List<VegetableDTO> viewVegetableList(String category) {
		List<VegetableDTO> list = vegetableDTORepository.findByType(category);
        if(list.isEmpty())
            return null;
        return list;

	}

	@Override
	public VegetableDTO viewVegetable(Integer vegetableId) {
		Optional<VegetableDTO> opt = vegetableDTORepository.findById(vegetableId);
		if(opt.isPresent()){
            VegetableDTO veg = opt.get();
            return veg;
        }
        return null;
	}

}
