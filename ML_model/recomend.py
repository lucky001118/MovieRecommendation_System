import pickle
import pandas as pd
from flask import Flask, request, jsonify
# from flask_cors import CORS

app = Flask(__name__)
# CORS(app)  # Enable CORS for cross-origin requests

# Load the model
movies_dict = pickle.load(open('movie_dict.pkl','rb'))
movies=pd.DataFrame(movies_dict)
similarity = pickle.load(open('similarity.pkl','rb'))

# def recommend(movie):
#     index = movies[movies['title'] == movie].index[0]
#     distances = sorted(list(enumerate(similarity[index])), reverse=True, key=lambda x: x[1])
#     recommended_movie_names = []
#     # recommended_movie_posters = []
#     for i in distances[1:6]:
#         # fetch the movie poster
#         movie_id = movies.iloc[i[0]].movie_id
#         # recommended_movie_posters.append(fetch_poster(movie_id))
#         recommended_movie_names.append(movies.iloc[i[0]].title)

#     return recommended_movie_names   #,recommended_movie_posters

def recommend(movie):
    movie_index = movies[movies['title'] == movie].index[0]
    distances = similarity[movie_index]
    movie_list = sorted(list(enumerate(distances)), reverse=True, key=lambda x: x[1]) [1:6]
    recomended_movies = []
    for i in movie_list:
        recomended_movies.append(movies.iloc[i[0]].title)
    return recomended_movies

#default call
@app.route('/')
def index():
    return "Welcome to the movie recommandation Machine Learning Model!"


@app.route("/recommend", methods=["POST"])
def recommend2():
    data = request.json
    movie_name = data.get("movie_name")

    print(movie_name)  # Debugging: Log the movie name received

    if not movie_name:
        return jsonify({"error": "Movie name is required"}), 400
    
    # Call the recommend function
    recommendations = recommend(movie_name)

    print(type(recommendations))  # Debugging: Log the type of recommendations

    # Check if the result contains an error
    if isinstance(recommendations, dict) and "error" in recommendations:
        return jsonify(recommendations), 404

    # If recommendations is already a list, no need to convert it
    return jsonify({"recommendations": recommendations})


@app.route("/movie-names", methods=["GET"])
def name():
    movies_names = movies['title'].values

    # Convert to list before serializing
    movies_names_list = movies_names.tolist()

    # Return as JSON response
    return jsonify({"name": movies_names_list})

if __name__ == "__main__":
    app.run(debug=True)
