package poly.persistance.mapper;

import config.Mapper;
import poly.dto.MovieDTO;
@Mapper("FoodMapper")
public interface IFoodMapper {
	int InsertMovieInfo(MovieDTO pDTO) throws Exception;
}
