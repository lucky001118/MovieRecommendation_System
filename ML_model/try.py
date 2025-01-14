import pickle
import json
import pandas as pd

# Load the model
movies_dict = pickle.load(open('movie_dict.pkl','rb'))
movies = pd.DataFrame(movies_dict)
similarity = pickle.load(open('similarity.pkl','rb'))

def recommend(movie):
    movie_index = movies[movies['title'] == movie].index[0]
    distances = similarity[movie_index]
    movie_list = sorted(list(enumerate(distances)), reverse=True, key=lambda x: x[1]) [1:6]

    # recommended_movie_names = []
    # # recommended_movie_posters = []
    # for i in distances[1:6]:
    #     # fetch the movie poster
    #     movie_id = movies.iloc[i[0]].movie_id
    #     # recommended_movie_posters.append(fetch_poster(movie_id))
    #     recommended_movie_names.append(movies.iloc[i[0]].title)

    recomended_movies = []
    for i in movie_list:
        recomended_movies.append(movies.iloc[i[0]].title)

    return recomended_movies   #,recommended_movie_posters

movie_name = "The Avengers"
recomended_movies = []
# recomended_movies = recommend("The Avengers")

movies_names = movies['title'].values
print(movies_names[1:100:2])

recommendations = recommend("The Avengers")

for i in recommendations:
    print(i)

print(type(movie_name))