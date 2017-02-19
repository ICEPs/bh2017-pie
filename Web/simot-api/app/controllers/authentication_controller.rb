class AuthenticationController < ActionController::API
  skip_before_action :authenticate_request, raise: false

  def authenticate
    command = AuthenticateUser.call params[:email], params[:password]

    user = User.where(:id => JsonWebToken.decode(command.result)['user_id']).take

    if command.success?
      render json: { auth_token: command.result,
                    user_id: user.id,
                    user_type: user.user_type }
    else
      render json: {error: command.errors}, status: :unauthorized
    end
  end

end
